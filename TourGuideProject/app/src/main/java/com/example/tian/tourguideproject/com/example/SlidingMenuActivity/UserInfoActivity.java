package com.example.tian.tourguideproject.com.example.SlidingMenuActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.adapter.UserInfoListAdapter;
import com.example.tian.tourguideproject.com.example.bean.SimpleUserInfoListItem;
import com.example.tian.tourguideproject.com.example.utils.HttpUtils;
import com.example.tian.tourguideproject.com.example.utils.ImageTools;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.tian.tourguideproject.MainActivity.usertel;

//import static com.example.tian.tourguideproject.MainActivity.usertel;

/**
 * Created by tian on 2016/11/22.
 */

public class UserInfoActivity extends Activity {



    private List<SimpleUserInfoListItem> userinfoList = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_list);

        setTopBar("个人信息");

        //初始化用户信息
//        userinfoList = initUserInfo();

        //从服务端获取用户的信息
        userinfoList = getUserInfo();
        usertel = userinfoList.get(3).getUserinfo_value();

        UserInfoListAdapter adapter = new UserInfoListAdapter(UserInfoActivity.this,
                R.layout.userinfo_list_item, userinfoList);
        ListView listView = (ListView)findViewById(R.id.userinfo_list_view);
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
                    case "":
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

                userinfoList = userInfoJsonTool(result);

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
                default:
                    break;
            }
        }
    };

    /**
     * 解析服务端返回的json
     */
    public List<SimpleUserInfoListItem> userInfoJsonTool(String str){

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
}
