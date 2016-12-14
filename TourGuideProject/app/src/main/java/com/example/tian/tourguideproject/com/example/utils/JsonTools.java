package com.example.tian.tourguideproject.com.example.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.tian.tourguideproject.com.example.bean.DetailGuideInfo;
import com.example.tian.tourguideproject.com.example.bean.OrdersMainInfoListItem;
import com.example.tian.tourguideproject.com.example.bean.SimpleGuideInfoListItem;
import com.example.tian.tourguideproject.com.example.bean.SimpleUserInfoListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tian on 2016/12/12.
 */

public class JsonTools {

    /**
     * 解析服务端返回的json，userinfo
     */
    public static List<SimpleUserInfoListItem> userInfoJsonTool(String str){

        byte[] data = null;
        List<SimpleUserInfoListItem> userinfoList = new ArrayList<>();
        ImageTools imageTools = new ImageTools();

        try {
            JSONObject jsonObject = new JSONObject(str);
            String image = jsonObject.getString("userImage");
            String name = jsonObject.getString("userName");
            String nickName = jsonObject.getString("userNickName");
            String phone = jsonObject.getString("userTel");
            String sex = jsonObject.getString("userSex");

            try {
                data = imageTools.getImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);//生成位图
            bitmap = imageTools.settingImage(bitmap);
            Drawable drawable = new BitmapDrawable(bitmap);

            SimpleUserInfoListItem userImage = new SimpleUserInfoListItem("头像", drawable);
            userinfoList.add(userImage);
            SimpleUserInfoListItem userName = new SimpleUserInfoListItem("姓名", name);
            userinfoList.add(userName);
            SimpleUserInfoListItem userNickname = new SimpleUserInfoListItem("昵称", nickName);
            userinfoList.add(userNickname);
            SimpleUserInfoListItem userTel = new SimpleUserInfoListItem("手机号", phone);
            userinfoList.add(userTel);
            SimpleUserInfoListItem userSex = new SimpleUserInfoListItem("性别", sex);
            userinfoList.add(userSex);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userinfoList;
    }

    /**
     * 修改用户个人信息的性别，解析返回的修改结果
     * true:修改成功    false:修改失败
     */
    public static String resetSexJsonTool(String str){

        String result = null;

        try {
            JSONObject jsonObject = new JSONObject(str);
            result = jsonObject.getString("result");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 解析服务端返回的数据,导游的简要信息
     * @param str
     */
    public static List<SimpleGuideInfoListItem> guideInfoJsonTool(String str){

        List<SimpleGuideInfoListItem> guideList = new ArrayList<>();

        ImageTools imageTools = new ImageTools();
        byte[] byteImage = null;

        try {
            JSONArray jsonArray = new JSONArray(str);

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String guideImagePath = jsonObject.getString("guideImage");
                String guideName = jsonObject.getString("guideName");
                String guideWorkAge = jsonObject.getString("guideWorkAge");
                String guideIntro = jsonObject.getString("guideIntro");
                String guidePrice = jsonObject.getString("guidePrice");
                String guideNumID = jsonObject.getString("guideNumID");

                try {
                    byteImage = imageTools.getImage(guideImagePath); //将网络图片转为二进制格式
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0,
                        byteImage.length);//生成位图
                bitmap = imageTools.settingImage(bitmap);
                Drawable guideImage = new BitmapDrawable(bitmap);

                SimpleGuideInfoListItem guideInfo = new SimpleGuideInfoListItem(guideImage,
                        guideName, guideWorkAge, guideIntro, guidePrice, guideNumID);

                guideList.add(guideInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return guideList;
    }

    /**
     * 解析服务端返回的数据
     * 导游的详细信息
     * @param str
     */
    public static DetailGuideInfo guideDetailInfoJsonTool(String str){

        DetailGuideInfo guideInfo = null;
        byte[] byteImage = null;
        ImageTools imageTools = new ImageTools();

        try {

            JSONObject jsonObject = new JSONObject(str);

            String guideImagePath = jsonObject.getString("guideImage");
            String guideName = jsonObject.getString("guideName");
            String guideWorkAge = jsonObject.getString("guideWorkAge");
            String guideIntro = jsonObject.getString("guideIntro");
            String guidePrice = jsonObject.getString("guidePrice");
            String guideNumID = jsonObject.getString("guideNumID");
            String guideLevel = jsonObject.getString("guideLevel");
            String guideSex = jsonObject.getString("guideSex");
            String guideAge = jsonObject.getString("guideAge");
            String guideLanguage = jsonObject.getString("guideLanguage");
            String guideCertificateID = jsonObject.getString("guideCertificateID");

            try {
                byteImage = imageTools.getImage(guideImagePath); //将网络图片转为二进制格式
            } catch (Exception e) {
                e.printStackTrace();
            }

            Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0,
                    byteImage.length);//生成位图
            bitmap = imageTools.settingImage(bitmap);
            Drawable guideImage = new BitmapDrawable(bitmap);

            guideInfo = new DetailGuideInfo(guideAge, guideSex, guideLevel,
                    guideCertificateID, guideImage, guideName, guideWorkAge, guideIntro,
                    guidePrice, guideNumID, guideLanguage);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return guideInfo;
    }

    /**
     * 解析服务端的发布订单的结果
     * true:修改成功    false:修改失败
     */
    public static String releaseInfoJsonTool(String str){

        String result = null;

        try {
            JSONObject jsonObject = new JSONObject(str);
            result = jsonObject.getString("result");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 解析服务端返回的数据
     * 全部订单的简要信息
     * 订单编号、参观人数、参观时间、订单金额/预算、订单状态
     */
    public static List<OrdersMainInfoListItem> ordersMainJsonTool(String str)
    {
        List<OrdersMainInfoListItem> ordersMainList = new ArrayList<>();
        try
        {
            JSONArray jsonArray = new JSONArray(str);
            for(int i = 0 ; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String bugdet = jsonObject.getString("budgetorTotal");
                String orderNo = jsonObject.getString("OrderNo");
                String orderStatus = jsonObject.getString("orderStatus");
                String personNum = jsonObject.getString("personNum");
                String visitTime = jsonObject.getString("visitTime");

                OrdersMainInfoListItem ordersMainInfo = new OrdersMainInfoListItem(
                        orderNo,personNum,visitTime,bugdet,orderStatus);

                ordersMainList.add(ordersMainInfo);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return ordersMainList;
    }
}
