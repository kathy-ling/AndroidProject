package com.example.tian.tourguideproject.com.example.SlidingMenuActivity;

import android.location.Location;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Handler;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.bean.RecordMyLocationService;
//import com.example.tian.tourguideproject.com.example.HttpService.GetLocationService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import static com.example.tian.tourguideproject.MainActivity.usertel;

public class MapTrackActivity extends AppCompatActivity {
    //地图控件初始化
    MapView mapView = null;
    BaiduMap mBaiduMap =null;

    //初始化相关控件
    EditText etDate = (EditText) findViewById(R.id.etDate);
    Button btnSearching = (Button) findViewById(R.id.btnTrackSearching);
    //定义定位模式
    private MyLocationConfiguration.LocationMode mCurrentMode;


    private static List<Location> location = new ArrayList<Location>();

    private double latitude;
    private double longitude;
    private String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext，该方法需在setContentView之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map_track);

        //获取地图控件引用
        mapView = (MapView)findViewById(R.id.bmapView);
        mBaiduMap = mapView.getMap();
        //选择地图类型，此为普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //开启定位
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode,true,null);
        mBaiduMap.setMyLocationConfigeration(config);
        MyLocationListenner myListener = new MyLocationListenner();

        LocationClient mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        //设置缩放级别
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(16).build()));



        //将定位数据每隔一段时间，存入数据库
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                RecordMyLocation();
            }
        }, 5000, 60*1000);
        //获取指定日期的位置信息
        //getLocations();

    }

    //将位置信息存入数据库
    public void RecordMyLocation()
    {
        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
        NameValuePair pair = new BasicNameValuePair("usertel", usertel);
        NameValuePair pair1 = new BasicNameValuePair("latitude", String.valueOf(latitude));
        NameValuePair pair2 = new BasicNameValuePair("longitude", String.valueOf(longitude));

        pairList.add(pair);
        pairList.add(pair1);
        pairList.add(pair2);

        RecordMyLocationService service = new RecordMyLocationService();
        if(latitude != 0.0 || longitude != 0.0){

            boolean recordResult = service.HttpPost(pairList);

            if(recordResult){
                Log.e("recordResult", "位置信息存储成功！");
            }else{
                Log.e("recordResult", "位置信息存储失败！！！！！！");
            }
        }
    }
   /* //从数据库读取位置信息
    public List<Location> getLocations()
    {
        btnSearching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        Message msg = new Message();

                        //获取用户输入的日期信息
                        dateString =etDate.getText().toString();

                        Log.e("location", "======================start to get location==============");

                        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                        NameValuePair pair = new BasicNameValuePair("username", username);
                        NameValuePair pair1 = new BasicNameValuePair("datefrom", dateString);
                        pairList.add(pair);
                        pairList.add(pair1);

                        GetLocationService getlocation= new GetLocationService();
                        location = getlocation.HttpPost(pairList);

                        if(location.size() != 0){

                            Log.e("location", location.toString());

                            if(location.size() > 2){
                                //在图上添加点，线等
                                //addCustomElementsDemo(location);
                            }

                            msg.what = 1;
                        }else{
                            msg.what = 2;
                        }
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
            }
        });
        return location;
    }*/
    //定位SDK监听函数
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);

            latitude = location.getLatitude();
            longitude = location.getLongitude();

        }
        public void onReceivePoi(BDLocation poiLocation) {
        }
    }



    protected void onDestroy(Bundle savedInstanceState)
    {
        super.onDestroy();
        mapView.onDestroy();
    }
    protected void onResume(Bundle savedInstanceState)
    {
        super.onResume();
        mapView.onResume();
    }
    protected void onPause(Bundle saveInstanceState)
    {
        super.onPause();
        mapView.onPause();
    }
}
