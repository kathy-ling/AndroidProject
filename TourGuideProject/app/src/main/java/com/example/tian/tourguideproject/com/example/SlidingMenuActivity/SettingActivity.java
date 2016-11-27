package com.example.tian.tourguideproject.com.example.SlidingMenuActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.bean.SimpleUserInfoListItem;

/**
 * Created by tian on 2016/11/22.
 */

public class SettingActivity extends Activity {

    private String[] list_item = {"修改密码", "关于"};

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_list);

        setTopBar("设置");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SettingActivity.this,
                android.R.layout.simple_list_item_1, list_item);

        ListView listView = (ListView)findViewById(R.id.setting_list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){

                    Intent intent = new Intent(SettingActivity.this, EditUserPasswdActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /*
    设置TopBar的标题和返回按钮
     */
    public void setTopBar(String title){

        ImageView back_img = (ImageView)findViewById(R.id.topbar_backkey);
        TextView topbar_title = (TextView)findViewById(R.id.topbar_title);

        topbar_title.setText(title);
        back_img.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
