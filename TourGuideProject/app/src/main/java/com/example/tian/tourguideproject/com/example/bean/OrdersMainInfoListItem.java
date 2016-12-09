package com.example.tian.tourguideproject.com.example.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by GaoXixi on 2016/12/7.
 */

public class OrdersMainInfoListItem {

    private String ordersVisitorsNum;  //参观人数
    private String ordersVisitorsTime; //参观时间
    private String ordersMoney;  //订单总额

    public OrdersMainInfoListItem(String ordersVisitorsNum,String ordersVisitorsTime,String ordersMoney)
    {
        this.ordersVisitorsNum = ordersVisitorsNum;
        this.ordersVisitorsTime = ordersVisitorsTime;
        this.ordersMoney = ordersMoney;
    }

    public String getOrdersVisitorsNum()
    {
        return ordersVisitorsNum;
    }
    public String getOrdersVisitorsTime()
    {
        return ordersVisitorsTime;
    }
    public String getOrdersMoney()
    {
        return ordersMoney;
    }


    public void setOrdersVisitorsNum(String ordersVisitorsNum)
    {
        this.ordersVisitorsNum = ordersVisitorsNum;
    }
    public void setOrdersVisitorsTime(String ordersVisitorsTime)
    {
        this.ordersVisitorsTime = ordersVisitorsTime;
    }
    public void setOrdersMoney(String ordersMoney)
    {
        this.ordersMoney = ordersMoney;
    }

}
