package com.example.tian.tourguideproject.com.example.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by tian on 2016/12/5.
 */

public class HttpUtils {

    public static final String BASE_URL="http://10.50.63.62:8080/SpringMe";

    public static String result = null;


    public static HttpGet getHttpGet(String url){
        HttpGet request = new HttpGet(url);
        return request;
    }

    public static HttpPost getHttpPost(String url){
        HttpPost request = new HttpPost(url);
        return request;
    }

    public static  String queryStringForPost(final String url, final List<NameValuePair> pairList){

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try{
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairList, "UTF-8");
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            int ret = response.getStatusLine().getStatusCode();

            if(ret == 200){

                HttpEntity httpEntity = response.getEntity();
                result = EntityUtils.toString(httpEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static  String queryStringForGet(String url){

        String result = null;

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = HttpUtils.getHttpGet(url);

        try {

            HttpResponse response = httpClient.execute(httpGet);
            int ret = response.getStatusLine().getStatusCode();

            if(ret == 200){

                HttpEntity httpEntity = response.getEntity();
                result = EntityUtils.toString(httpEntity);

                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = "网络异常ClientProtocolException"+e;
            return result;
        }
        catch (IOException e) {
            e.printStackTrace();
            result = "网络异常IOException"+ e;
            return result;
        }
        return null;
    }
}
