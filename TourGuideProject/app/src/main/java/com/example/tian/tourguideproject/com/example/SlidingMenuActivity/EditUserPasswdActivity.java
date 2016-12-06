package com.example.tian.tourguideproject.com.example.SlidingMenuActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.HttpService.ResetUserPswService;

/**
 * Created by tian on 2016/11/22.
 * 修改用户密码
 */

public class EditUserPasswdActivity extends Activity implements View.OnClickListener{

    private EditText userPhone;
    private EditText userPsw;
    private EditText userPswRepeat;
    private Button savePsw;

    private String userPhoneString;
    private String userPswString;
    private String userPswRepeatString;

    boolean bool = false;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_passwd);

        setTopBar("修改密码");

        initView();
    }

    public void initView(){

        userPhone = (EditText)findViewById(R.id.edit_user_phone_txt);
        userPsw = (EditText)findViewById(R.id.edit_userPsw);
        userPswRepeat = (EditText)findViewById(R.id.edit_userPsw_again);
        savePsw = (Button)findViewById(R.id.save_userpsw_btn);

        savePsw.setOnClickListener(this);
    }

    /**
     * 设置TopBar的标题和返回按钮
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

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.save_userpsw_btn:

                boolean bool = resetUserPsw();
                if(bool){
                    Toast.makeText(EditUserPasswdActivity.this, "修改密码成功",
                            Toast.LENGTH_SHORT).show();
                    savePsw.setBackgroundColor(Color.GRAY);
                    savePsw.setClickable(false);
                }else{
                    Toast.makeText(EditUserPasswdActivity.this, "修改密码失败！",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 与服务端交互，重置用户密码
     * @return
     */
    public boolean resetUserPsw(){

        userPhoneString = userPhone.getText().toString();
        userPswString = userPsw.getText().toString();
        userPswRepeatString = userPswRepeat.getText().toString();

        if(!userPswRepeatString.equals(userPswString)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(EditUserPasswdActivity.this);
            dialog.setTitle("密码不一致！");
            dialog.setMessage("请重新输入密码");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    userPswRepeat.setText("");
                }
            });
            dialog.show();
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                ResetUserPswService resetUserPswService = new ResetUserPswService();
                bool = resetUserPswService.executeHttpGet(userPhoneString, userPswString);

                Message msg = new Message();

                //修改密码失败
                if(!bool){
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return bool;
    }

    private Handler handler = new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e("resetPsw", "resetPsw failed!");

                    break;
                default:
                    break;
            }
        }
    };
}
