package com.example.tian.tourguideproject.com.example.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tian on 2016/11/23.
 * 从网络中获取图片,得到图片的二进制格式
 */

public class ImageTools {

    /**
     * 设置bitmap图片的大小
     * @param bitmap
     * @return
     */
    public Bitmap settingImage(Bitmap bitmap){

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 设置想要的大小
        int newWidth = 500;
        int newHeight = 500;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap mbitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);

        return mbitmap;
    }

    /**
     *将网络图片转为二进制格式
     */
    public static byte[] getImage(String path) throws Exception
    {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream =  conn.getInputStream();//通过输入流获取图片数据
        return readInputStream(inStream);//得到图片的二进制数据
    }

    public static byte[] readInputStream(InputStream instream) throws Exception
    {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[]  buffer = new byte[1204];
        int len = 0;
        while ((len = instream.read(buffer)) != -1)
        {
            outStream.write(buffer,0,len);
        }
        instream.close();
        return outStream.toByteArray();
    }
}
