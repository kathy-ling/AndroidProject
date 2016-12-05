package com.example.tian.tourguideproject.com.example.HttpService;

import com.example.tian.tourguideproject.com.example.bean.Location;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高茜茜09 on 2016/12/2.
 */

public class getLocationService {
    private static String URL = "http://10.50.63.62:8080/SpringMe/getLocationofvisitors.do";
    List<Location> locations = new ArrayList<Location>();

    public List<Location> HttpPost(final List<NameValuePair> pairList)
    {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URL);

        try{
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairList, "UTF-8");
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            int ret = response.getStatusLine().getStatusCode();

            if(ret == 200){

                HttpEntity httpEntity = response.getEntity();
                String result = EntityUtils.toString(httpEntity);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray array = jsonObject.getJSONArray("location");

                    for(int i = 0; i < array.length(); i++){
                        JSONObject jsonObjectSon= (JSONObject)array.opt(i);
                        String tmp1 = jsonObjectSon.getString("latitude");
                        String tmp2 = jsonObjectSon.getString("longitude");

//						Log.e("latitude", tmp1);
//						Log.e("longitude", tmp2.toString());

                        locations.add(new Location(tmp1, tmp2));
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(URL);
                try
                {
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairList, "UTF-8");
                    httpPost.setEntity(entity);

                    HttpResponse response = httpClient.execute(httpPost);
                    int ret = response.getStatusLine().getStatusCode();
                    if(ret == 200)
                    {
                        HttpEntity httpEntity = response.getEntity();
                        String result = EntityUtils.toString(httpEntity);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray array = jsonObject.getJSONArray("locations");

                            for(int i = 0; i < array.length(); i++){
                                JSONObject jsonObjectSon= (JSONObject)array.opt(i);
                                String tmp1 = jsonObjectSon.getString("latitude");
                                String tmp2 = jsonObjectSon.getString("longitude");

                                locations.add(new Location(tmp1, tmp2));
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        });
        thread.start();*/
        return locations;
    }
}
