package com.example.tian.tourguideproject.com.example.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

/**
 * Created by tian on 2016/12/2.
 */

public class LocalReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "接收广播成功", Toast.LENGTH_SHORT).show();
    }
}
