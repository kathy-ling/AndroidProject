package com.example.tian.tourguideproject.com.example.SlidingMenuActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tian.tourguideproject.R;

/**
 * Created by tian on 2016/11/22.
 */

public class EditUserNameActivity extends Activity{

    private EditText edit_userName;
    private Button save_userName;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_username);

        setTopBar("更改名字");
        edit_userName = (EditText)findViewById(R.id.edit_username);
        save_userName = (Button)findViewById(R.id.save_username_btn);

        edit_userName.setText("");
        save_userName.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                /*
                与服务端交互，修改用户名
                 */
                Toast.makeText(EditUserNameActivity.this, "username changed", Toast.LENGTH_LONG).show();
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
