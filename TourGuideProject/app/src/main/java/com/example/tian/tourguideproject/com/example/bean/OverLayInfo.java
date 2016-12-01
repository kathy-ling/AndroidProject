package com.example.tian.tourguideproject.com.example.bean;




import com.example.tian.tourguideproject.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 高茜茜09 on 2016/11/29.
 */

public class OverLayInfo implements Serializable {
    private double latitude;
    private double longitude;
    private int imgId; //景点图片ID
    private String name;  //景点名称
    private String decription; //景点描述

    public static List<OverLayInfo> infos = new ArrayList<OverLayInfo>();

    static
    {
        infos.add(new OverLayInfo(34.394395,109.291943, R.drawable.ic_museum,"兵马俑博物馆","秦始皇兵马俑博物馆位于陕西省西安市临潼区城东，是中国第一个封建皇帝秦始皇嬴政之陵园中一处大型从葬坑，陵园面积218万平方米。博物馆以秦始皇兵马俑为基础，在兵马俑坑原址上建立的遗址类博物馆，也是中国最大的古代军事博物馆。\n" +
                "\n秦始皇兵马俑博物馆共有一、二、三号3个兵马俑坑。一号坑是一个以战车和步兵相间的主力军阵，总面积14260平方米，约有6000个真人大小的陶俑。二号坑是秦俑坑中的精华，面积6000平方米，由四个单元组成，四个方阵由战车、骑兵、驽兵混合编组，严整有序，无懈可击。三号坑是军阵的指挥系统，面积524平方米。\n" +
                "\n秦兵马俑坑发现于1974-1976年，秦始皇兵马俑博物馆1979年向国内外公开开放。兵马俑的发现被誉为世界第八大奇迹、二十世纪考古史上的伟大发现。\n" +
                "\n秦始皇兵马俑为研究秦代军事、文化和经济提供了丰富的实物资料。兵马俑是雕塑艺术的宝库，为中华民族灿烂的古老文化增添了光彩，也给世界艺术史补充了光辉的一页。"));
        infos.add(new OverLayInfo(34.389453,109.286123,R.drawable.ic_pit,"兵马俑1号坑","一号坑为东西向的长方形坑，长230米，宽62米，总面积14260平方米，四周各有五个门道。坑东西两端有长廊，南北两侧各有一边廊，中间为九条东西向过洞，过洞之间以夯土墙间隔。这个坑以车兵为主体，车、步兵成矩形联合编队。军阵主体面向东，在南、北、西边廊中各有一排武士面向外，担任护翼和后卫；东面三排武士为先锋。九个过洞内排列着战车与步兵的庞大主体军阵，每个过洞内有四列武士，有的穿战袍，有的着铠甲，中间配有战车，每辆战车后有 御手一名，车士两名。"));
    }
    public OverLayInfo()
    {

    }
    public OverLayInfo(double latitude,double longitude, int imgId, String name,String decription) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.imgId =imgId;
        this.name = name;
        this.decription = decription;
    }
    public double getLatitude()
    {
        return latitude;
    }
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }
    public double getLongitude()
    {
        return longitude;
    }
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getImgId()
    {
        return imgId;
    }
    public void setImgId(int imgId)
    {
        this.imgId = imgId;
    }
    public void setDecription(String decription)
    {
        this.decription = decription;
    }
    public String getDescription()
    {
        return decription;
    }

}
