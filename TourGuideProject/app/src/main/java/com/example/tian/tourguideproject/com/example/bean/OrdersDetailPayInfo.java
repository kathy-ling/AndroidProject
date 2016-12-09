package com.example.tian.tourguideproject.com.example.bean;

/**
 * Created by GaoXixi on 2016/12/9.
 */

public class OrdersDetailPayInfo {

    private String ordersUserName;  //预定人姓名
    private String ordersUserTel; //预定人联系电话
    private String ordersVisitorsNum; //参观人数
    private String ordersVisitorsTime; //参观时间
    private String ordersUserMoney;  //订单总额

    private String ordersGuideNumId; //导游证号

    private String ordersPayTimeId; //订单编号
    private String ordersPayTimeStart; //下单时间

    public OrdersDetailPayInfo(String ordersUserName,String ordersUserTel,String ordersVisitorsNum,String ordersVisitorsTime,String ordersUserMoney,String ordersGuideNumId,String ordersPayTimeId,String ordersPayTimeStart)
    {
        this.ordersUserName = ordersUserName;
        this.ordersUserTel = ordersUserTel;
        this.ordersVisitorsNum = ordersVisitorsNum;
        this.ordersVisitorsTime = ordersVisitorsTime;
        this.ordersUserMoney = ordersUserMoney;
        this.ordersGuideNumId = ordersGuideNumId;
        this.ordersPayTimeId = ordersPayTimeId;
        this.ordersPayTimeStart = ordersPayTimeStart;
    }

    public String getOrdersUserName()
    {
        return ordersUserName;
    }
    public String getOrdersUserTel()
    {
        return ordersUserTel;
    }
    public String getOrdersVisitorsNum()
    {
        return ordersVisitorsNum;
    }
    public String getOrdersVisitorsTime()
    {
        return ordersVisitorsTime;
    }
    public String getOrdersUserMoney()
    {
        return ordersUserMoney;
    }
    public String getOrdersGuideNumId()
    {
        return ordersGuideNumId;
    }
    public String getOrdersPayTimeId()
    {
        return ordersPayTimeId;
    }
    public String getOrdersPayTimeStart()
    {
        return ordersPayTimeStart;
    }

    public void setOrdersUserName(String ordersUserName)
    {
        this.ordersUserName = ordersUserName;
    }
    public void setOrdersUserTel(String ordersUserTel)
    {
        this.ordersUserTel = ordersUserTel;
    }
    public void setOrdersVisitorsNum(String ordersVisitorsNum)
    {
        this.ordersVisitorsNum = ordersVisitorsNum;
    }
    public void setOrdersVisitorsTime(String ordersVisitorsTime)
    {
        this.ordersVisitorsTime = ordersVisitorsTime;
    }
    public void setOrdersUserMoney(String ordersUserMoney)
    {
        this.ordersUserMoney= ordersUserMoney;
    }
    public void setOrdersGuideNumId(String ordersGuideNumId)
    {
        this.ordersGuideNumId = ordersGuideNumId;
    }
    public void setOrdersPayTimeId(String ordersPayTimeId)
    {
        this.ordersPayTimeId = ordersPayTimeId;
    }
    public void setOrdersPayTimeStart(String ordersPayTimeStart)
    {
        this.ordersPayTimeStart = ordersPayTimeStart;
    }
}
