package com.example.tian.tourguideproject.com.example.bean;

/**
 * Created by 高茜茜09 on 2016/12/2.
 */

public class Location {
    //纬度值
    private String latitude;
    //经度值
    private String longitude;

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public Location(String latitude, String longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
