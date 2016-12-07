package com.example.tian.tourguideproject.com.example.utils;

/**
 * Created by tian on 2016/12/7.
 * 对性别进行转换
 * 1：女      0：男
 */

public class SexExchange {

    public int SexStringtoInt(String sexString){

        int sexInt = 0;

        if(sexString.equals("男")){
            sexInt = 0;
        }else if(sexString.equals("女")){
            sexInt = 1;
        }

        return sexInt;
    }

    public String SexInttoString(int sexInt){

        String sexString = null;

        if(sexInt == 0){
            sexString = "男";
        }else if(sexInt == 1){
            sexString = "女";
        }

        return sexString;
    }
}
