package com.example.tian.tourguideproject.com.example.SlidingMenuActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Message;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.HttpService.getLocationService;
import com.example.tian.tourguideproject.com.example.bean.RecordMyLocationService;
import com.example.tian.tourguideproject.com.example.HttpService.getLocationService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import static com.baidu.mapapi.BMapManager.getContext;
import static com.example.tian.tourguideproject.MainActivity.usertel;
import com.example.tian.tourguideproject.com.example.bean.Location;

public class MapTrackActivity extends AppCompatActivity implements View.OnClickListener{
    //地图控件初始化
    MapView mapView = null;
    BaiduMap mBaiduMap =null;

    //定义定位模式
    private MyLocationConfiguration.LocationMode mCurrentMode;

    private static List<Location> locationArrayList = new ArrayList<Location>();

    Button btnSearching;
    EditText etDate;
    private double latitude;
    private double longitude;
    private String dateString;
    private Calendar m_date = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext，该方法需在setContentView之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map_track);

        //初始化控件
        initView();

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
        getLocations();

    }

    //初始化控件
    private void initView() {
        //获取地图控件引用
        mapView = (MapView)findViewById(R.id.bmapView);
        btnSearching = (Button) findViewById(R.id.btnTrackSearching);
        etDate = (EditText) findViewById(R.id.etDate);
        //禁止弹出软键盘
        etDate.setInputType(InputType.TYPE_NULL);
        //添加点击事件
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Dialog dialog = onCreateDatePickDialog(etDate);
                    dialog.show();
                }
        });
    }

    //创建显示日期对话框
    protected Dialog onCreateDatePickDialog(final EditText editText) {
        Dialog dialog = null;
        m_date = Calendar.getInstance();
        try
        {
            dialog = new DatePickerDialog(MapTrackActivity.this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
                    editText.setText(dateFormat(year, (month + 1), dayOfMonth));
                }
            }, m_date.get(Calendar.YEAR), // 传入年份
                    m_date.get(Calendar.MONTH), // 传入月份
                    m_date.get(Calendar.DAY_OF_MONTH) // 传入天数
            );

        }
        catch (Exception e)
        {}
        return dialog;
    }
    //格式化日期
    private String dateFormat(int year, int month, int day) {

        String m = String.valueOf(month);
        String d = String.valueOf(day);

        if(month < 10) {
            m = "0" + m;
        }
        if(day < 10) {
            d = "0" + d;
        }

        return year + "-" + m + "-" + d;
    }
    //将位置信息存入数据库
    public void RecordMyLocation()
    {
        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
        NameValuePair pair_latitude = new BasicNameValuePair("latitude", String.valueOf(latitude));
        NameValuePair pair_longtitude = new BasicNameValuePair("longitude", String.valueOf(longitude));
        NameValuePair pair_usertel = new BasicNameValuePair("usertel", usertel);

        pairList.add(pair_latitude);
        pairList.add(pair_longtitude);
        pairList.add(pair_usertel);

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

    //获取指定日期的位置信息
    public List<Location> getLocations()
    {
        btnSearching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        //获取用户输入的日期信息
                        dateString = etDate.getText().toString();
                        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                        NameValuePair pair_usertel = new BasicNameValuePair("usertel", usertel);
                        NameValuePair pair_dateString = new BasicNameValuePair("dateString",dateString);

                        pairList.add(pair_usertel);
                        pairList.add(pair_dateString);

                        getLocationService getlocation= new getLocationService();
                        locationArrayList = getlocation.HttpPost(pairList);
                        if(locationArrayList.size() != 0){

                            Log.e("location", locationArrayList.toString());

                            if(locationArrayList.size() > 2){
                                addCustomElementsDemo(locationArrayList);
                            }

                            message.what = 1;
                        }else{
                            message.what = 2;
                        }
                        handler.sendMessage(message);

                    }
                }).start();
            }
        });
        return locationArrayList;
    }
    private  Handler handler = new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e("getLocation", "get!");
                    break;
                case 2:
                    Log.e("getLocation", "nonoon!");
                    Toast.makeText(getContext(), "此时间段内没有位置信息记录！",
                            Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };
    /**
     * 在地图上添加点、线等
     */
    public void addCustomElementsDemo(List<Location> location) {

        if(location != null){

            List<LatLng> points = new ArrayList<LatLng>();

            for(int i = 0; i < location.size(); i++){
                double lati = Double.parseDouble(location.get(i).getLatitude());
                double longi = Double.parseDouble(location.get(i).getLongitude());

                LatLng llDot = new LatLng(lati, longi);
                points.add(llDot);
            }
            //添加折线
            OverlayOptions ooPolyline = new PolylineOptions().width(10)
                    .color(0xAAFF0000).points(points);
            mBaiduMap.addOverlay(ooPolyline);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_guide_btn:

                break;
        }
    }

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
