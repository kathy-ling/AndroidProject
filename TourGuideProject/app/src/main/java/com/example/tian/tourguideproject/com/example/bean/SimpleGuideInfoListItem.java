package com.example.tian.tourguideproject.com.example.bean;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by tian on 2016/11/25.
 * 导游的简要信息
 * 查询后以列表形式显示
 */

public class SimpleGuideInfoListItem implements Serializable {

    private Drawable guideImage; //头像
    private String guideName;  //姓名
    private String guideWorkAge;  //工作年限
    private String guideIntro;  //个人介绍
    private String guidePrice;  //价钱
    private String guideNumID;  //导游身份证号

    public SimpleGuideInfoListItem(Drawable guideImage, String guideName,
                                   String guideWorkAge, String guideIntro,
                                   String guidePrice, String guideNumID) {
        this.guideImage = guideImage;
        this.guideName = guideName;
        this.guideWorkAge = guideWorkAge;
        this.guideIntro = guideIntro;
        this.guidePrice = guidePrice;
        this.guideNumID = guideNumID;
    }

    public String getGuideNumID() {
        return guideNumID;
    }

    public Drawable getGuideImage() {
        return guideImage;
    }

    public String getGuideName() {
        return guideName;
    }

    public String getGuideWorkAge() {
        return guideWorkAge;
    }

    public String getGuideIntro() {
        return guideIntro;
    }

    public String getGuidePrice() {
        return guidePrice;
    }

    public void setGuideImage(Drawable guideImage) {
        this.guideImage = guideImage;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public void setGuideWorkAge(String guideWorkAge) {
        this.guideWorkAge = guideWorkAge;
    }

    public void setGuideIntro(String guideIntro) {
        this.guideIntro = guideIntro;
    }

    public void setGuidePrice(String guidePrice) {
        this.guidePrice = guidePrice;
    }

    public void setGuideNumID(String guideNumID) {
        this.guideNumID = guideNumID;
    }
}
