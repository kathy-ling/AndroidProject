package com.example.tian.tourguideproject.com.example.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by tian on 2016/12/1.
 * 导游信息
 * 除了导游简要信息之外的其他信息
 */

public class DetailGuideInfo {

    private String guideAge;  //导游年龄
    private String guideSex;  //性别
    private String guideLevel;  //导游级别
    private String guideCertificateID;  //导游证号
    private Drawable guideImage; //头像
    private String guideName;  //姓名
    private String guideWorkAge;  //工作年限
    private String guideIntro;  //个人介绍
    private String guidePrice;  //价钱
    private String guideNumID;  //导游身份证号

    public DetailGuideInfo(String guideAge, String guideSex,
                           String guideLevel, String guideCertificateID,
                           Drawable guideImage, String guideName,
                           String guideWorkAge, String guideIntro,
                           String guidePrice, String guideNumID) {
        this.guideAge = guideAge;
        this.guideSex = guideSex;
        this.guideLevel = guideLevel;
        this.guideCertificateID = guideCertificateID;
        this.guideImage = guideImage;
        this.guideName = guideName;
        this.guideWorkAge = guideWorkAge;
        this.guideIntro = guideIntro;
        this.guidePrice = guidePrice;
        this.guideNumID = guideNumID;
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

    public String getGuideAge() {
        return guideAge;
    }

    public String getGuideSex() {
        return guideSex;
    }

    public String getGuideLevel() {
        return guideLevel;
    }

    public String getGuideCertificateID() {
        return guideCertificateID;
    }

    public String getGuideNumID() {
        return guideNumID;
    }

    public void setGuideAge(String guideAge) {
        this.guideAge = guideAge;
    }

    public void setGuideSex(String guideSex) {
        this.guideSex = guideSex;
    }

    public void setGuideLevel(String guideLevel) {
        this.guideLevel = guideLevel;
    }

    public void setGuideCertificateID(String guideCertificateID) {
        this.guideCertificateID = guideCertificateID;
    }

    public void setGuideNumID(String guideNumID) {
        this.guideNumID = guideNumID;
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
}
