package com.example.tian.tourguideproject.com.example.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by GaoXixi on 2016/12/7.
 */

public class OrdersMainInfoListItem {

    private String ordersID;  //订单编号
    private String ordersVisitorsNum;  //参观人数
    private String ordersVisitorsTime; //参观时间
    private String ordersMoney;  //订单总额
    private String ordersStatus;  //订单状态

    public OrdersMainInfoListItem(String ordersID,String ordersVisitorsNum,
                                  String ordersVisitorsTime,String ordersMoney,
                                  String ordersStatus)
    {
        this.ordersID = ordersID;
        this.ordersVisitorsNum = ordersVisitorsNum;
        this.ordersVisitorsTime = ordersVisitorsTime;
        this.ordersMoney = ordersMoney;
        this.ordersStatus = ordersStatus;
    }

    public String getOrdersID()
    {
        return ordersID;
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
    public String getOrdersStatus()
    {
        return ordersStatus;
    }


    public void setOrdersID(String ordersID)
    {
        this.ordersID = ordersID;
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

    public void setOrdersStatus(String ordersStatus)
    {
        this.ordersStatus = ordersStatus;
    }
}
