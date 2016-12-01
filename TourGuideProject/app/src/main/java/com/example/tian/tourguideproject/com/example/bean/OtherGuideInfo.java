package com.example.tian.tourguideproject.com.example.bean;

/**
 * Created by tian on 2016/12/1.
 * 导游信息
 * 除了导游简要信息之外的其他信息
 */

public class OtherGuideInfo {

    private String guideAge;  //导游年龄
    private String guideSex;  //性别
    private String guideLevel;  //导游级别
    private String guideCertificateID;  //导游证号
    private String guideNumID;  //身份证号

    public OtherGuideInfo(String guideAge, String guideSex,
                          String guideLevel, String guideCertificateID, String guideNumID) {
        this.guideAge = guideAge;
        this.guideSex = guideSex;
        this.guideLevel = guideLevel;
        this.guideCertificateID = guideCertificateID;
        this.guideNumID = guideNumID;
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
}
