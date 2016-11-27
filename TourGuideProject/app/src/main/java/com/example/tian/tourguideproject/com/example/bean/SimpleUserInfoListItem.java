package com.example.tian.tourguideproject.com.example.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by tian on 2016/11/21.
 */

public class SimpleUserInfoListItem {

    private String userinfo_name;
    private String userinfo_value;
    private Drawable image;

    public SimpleUserInfoListItem() {

    }

    public SimpleUserInfoListItem(String userinfo_name, String userinfo_value) {
        this.userinfo_name = userinfo_name;
        this.userinfo_value = userinfo_value;
    }

    public SimpleUserInfoListItem(String userinfo_name, Drawable image) {
        this.userinfo_name = userinfo_name;
        this.image = image;
    }

    public SimpleUserInfoListItem(String userinfo_name, String userinfo_value, Drawable image) {
        this.userinfo_name = userinfo_name;
        this.userinfo_value = userinfo_value;
        this.image = image;
    }

    public String getUserinfo_name() {
        return userinfo_name;
    }

    public String getUserinfo_value() {
        return userinfo_value;
    }

    public Drawable getImage() {
        return image;
    }

    public void setUserinfo_name(String userinfo_name) {
        this.userinfo_name = userinfo_name;
    }

    public void setUserinfo_value(String userinfo_value) {
        this.userinfo_value = userinfo_value;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
