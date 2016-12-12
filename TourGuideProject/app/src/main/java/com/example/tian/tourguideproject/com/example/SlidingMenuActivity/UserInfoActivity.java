package com.example.tian.tourguideproject.com.example.SlidingMenuActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.Activity.ReleaseOrderActivity;
import com.example.tian.tourguideproject.com.example.adapter.UserInfoListAdapter;
import com.example.tian.tourguideproject.com.example.bean.SimpleUserInfoListItem;
import com.example.tian.tourguideproject.com.example.utils.HttpUtils;
import com.example.tian.tourguideproject.com.example.utils.ImageTools;
import com.example.tian.tourguideproject.com.example.utils.JsonTools;
import com.example.tian.tourguideproject.com.example.utils.SexExchange;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import static com.example.tian.tourguideproject.MainActivity.usertel;


/**
 * Created by tian on 2016/11/22.
 */

public class UserInfoActivity extends Activity {


    private UserInfoListAdapter adapter;
    private ListView listView;
    private List<SimpleUserInfoListItem> userinfoList = new ArrayList<>();

    private final String[] sexItem = {"男", "女"};

    private String select_item;
    private String changedSex;

    private List<NameValuePair> params = new ArrayList<NameValuePair>();

    Thread resetSetThread;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_list);

        setTopBar("个人信息");

        //初始化用户信息
//        userinfoList = initUserInfo();

        //从服务端获取用户的信息
        userinfoList = getUserInfo();
        usertel = userinfoList.get(3).getUserinfo_value();
        select_item = changedSex = userinfoList.get(4).getUserinfo_value();

        adapter = new UserInfoListAdapter(UserInfoActivity.this,
                R.layout.userinfo_list_item, userinfoList);
        listView = (ListView)findViewById(R.id.userinfo_list_view);
        listView.setAdapter(adapter);
        handleListViewItemClick(listView);

    }

    /**
     * 处理ListView每个item的点击事件
     */
    public void handleListViewItemClick(ListView listView){

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SimpleUserInfoListItem userinfo = userinfoList.get(position);
                String info_name = userinfo.getUserinfo_name();

                switch(info_name){
                    case "姓名":
                        Intent intent = new Intent(UserInfoActivity.this, EditUserNameActivity.class);
                        startActivity(intent);
                        break;
                    case "性别":
                        ChangeSexDialog(sexItem, position);
                        break;
                    default:
                        break;

                }
            }
        });
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

    public List<SimpleUserInfoListItem> initUserInfo(){

        int[] itemIconRes = {R.drawable.user_image};

        SimpleUserInfoListItem userImage = new SimpleUserInfoListItem("头像",
                getResources().getDrawable(itemIconRes[0]));
        userinfoList.add(userImage);
        SimpleUserInfoListItem userName = new SimpleUserInfoListItem("name", "王五");
        userinfoList.add(userName);
        SimpleUserInfoListItem userNickname = new SimpleUserInfoListItem("昵称", "碧玉");
        userinfoList.add(userNickname);
        SimpleUserInfoListItem userTel = new SimpleUserInfoListItem("Tel", "19247897");
        userinfoList.add(userTel);
        SimpleUserInfoListItem userSex = new SimpleUserInfoListItem("Sex", "女");
        userinfoList.add(userSex);

        return userinfoList;
    }

    /**
     * 用于从服务端获取用户的信息
     */
    public  List<SimpleUserInfoListItem> getUserInfo(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                List <NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userTel", "15029319152"));

                String url = HttpUtils.BASE_URL + "/getphonenumber.do";
                String result = HttpUtils.queryStringForPost(url, params);

                userinfoList = JsonTools.userInfoJsonTool(result);

                Message msg = new Message();

                if(userinfoList != null){

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

        return userinfoList;
    }


    private Handler handler = new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e("userInfo", "get user info OK!");
                    break;
                case 2:
                    Log.e("sex", "reset sex OK!");

                    break;
                case 3:
                    Log.e("sex", "reset sex failed!");
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 点击对话框，修改用户的性别信息
     * @param sexItem ，修改后的性别
     * @param position ， listitem的position
     */
    public void ChangeSexDialog(final String[] sexItem, final int position){

        AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoActivity.this);

        builder.setTitle("请选择");
        builder.setSingleChoiceItems(sexItem, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                select_item = sexItem[which].toString();
                changedSex = select_item;
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(UserInfoActivity.this, select_item,
                        Toast.LENGTH_SHORT).show();

                resetUserSex();

                /**
                 * 更新ListView item
                 */
                userinfoList.remove(position);
                userinfoList.add(new SimpleUserInfoListItem("性别", select_item));
                adapter.notifyDataSetChanged();

                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public void resetUserSex(){

        Log.e("in reset", "go into reset set");

        Thread resetSetThread = new Thread(new Runnable() {
            @Override
            public void run() {

                String sex = null;
                Message msg = new Message();

                SexExchange sexExchange = new SexExchange();
                sex = String.valueOf(sexExchange.SexStringtoInt(select_item));

                params.add(new BasicNameValuePair("userPhone", "15029319152"));
                params.add(new BasicNameValuePair("userSex", sex));

//                String url = HttpUtils.BASE_URL + "/updatevisitorsex.do";
                String url = "http://10.50.63.83:8080/SpringMe" + "/updatevisitorsex.do";
                String result = HttpUtils.queryStringForPost(url, params);

                String ret = JsonTools.resetSexJsonTool(result);

                if(ret.equals("true")){
                    msg.what = 2;
                }else if(ret.equals("false")){
                    msg.what = 3;
                }
                handler.sendMessage(msg);
            }
        });

        resetSetThread.start();

        try {
            resetSetThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
