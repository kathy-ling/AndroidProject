package com.example.tian.tourguideproject.com.example.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by tian on 2016/11/25.
 */

public class SimpleGuideInfoListItem {

    private Drawable guideImage;
    private String guideName;
    private String guideWorkAge;
    private String guideIntro;
    private String guidePrice;

    public SimpleGuideInfoListItem(Drawable guideImage, String guideName,
                                   String guideWorkAge, String guideIntro,
                                   String guidePrice) {
        this.guideImage = guideImage;
        this.guideName = guideName;
        this.guideWorkAge = guideWorkAge;
        this.guideIntro = guideIntro;
        this.guidePrice = guidePrice;
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
}
