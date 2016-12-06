package com.example.tian.tourguideproject.com.example.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.Fragments.ClickOverlayFragment;
import com.example.tian.tourguideproject.com.example.bean.OverLayInfo;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    //地图控件初始化
    MapView mapView = null;
    BaiduMap mBaiduMap =null;
    //定义定位模式
    private MyLocationConfiguration.LocationMode mCurrentMode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //获取地图控件引用
        View view = inflater.inflate(R.layout.fragment_map, null);
        mapView = (MapView) view.findViewById(R.id.bmapView);
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

        LocationClient mLocClient = new LocationClient(getContext());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        //设置缩放级别
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(17).build()));

        //添加覆盖物
        addInfosOverLay(OverLayInfo.infos);
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker) {
                ClickOverlayFragment colFragment = new ClickOverlayFragment();
                OverLayInfo info = (OverLayInfo) marker.getExtraInfo().get("info");
                Bundle bundle = new Bundle();
                bundle.putSerializable("Info",info);
                colFragment.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_fragment,colFragment)
                        .commit();
                return true;
            }
        });
        return view;
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
                    .direction(100).latitude(34.391024)
                    .longitude(109.289078).build();
            mBaiduMap.setMyLocationData(locData);

        }
        public void onReceivePoi(BDLocation poiLocation) {
        }
    }


    public MapFragment() {
        // Required empty public constructor
    }

    public void addInfosOverLay(List<OverLayInfo> infos)
    {
        LatLng latLng = null;
        OverlayOptions overlayOptions = null;
        Marker marker = null;
        for(OverLayInfo info : infos)
        {
            latLng = new LatLng(info.getLatitude(),info.getLongitude());
            overlayOptions = new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_overlay))
                    .zIndex(9)
                    .draggable(false)
                    .visible(true);
            //在地图上添加Marker并显示
            marker = (Marker) mBaiduMap.addOverlay(overlayOptions);
            Bundle bundle = new Bundle();
            bundle.putSerializable("info",info);
            marker.setExtraInfo(bundle);
            mapView.invalidate();
        }

    }

}
