package com.example.tian.tourguideproject.com.example.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.Fragments.GuideDetailInfoFragment;
import com.example.tian.tourguideproject.com.example.Fragments.GuideEvaluationFragment;
import com.example.tian.tourguideproject.com.example.Fragments.GuideHistoryGroupFragment;
import com.example.tian.tourguideproject.com.example.adapter.MyFragmentAdapter;
import com.example.tian.tourguideproject.com.example.bean.DetailGuideInfo;
import com.example.tian.tourguideproject.com.example.utils.HttpUtils;
import com.example.tian.tourguideproject.com.example.utils.ImageTools;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tian on 2016/11/28.
 * 导游的详细信息
 * 由她的信息、他的评价、他的团三个可滑动页面组成
 */

public class GuideDetailInfosActivity extends FragmentActivity implements
        ViewPager.OnPageChangeListener, View.OnClickListener {

    /*
    ViewPager包含的页面
     */
    private View detailInfoPage;  //他的信息
    private View evaluationPage;   //他的评价
    private View historyGroupPage;  //他的团

    /*
    ViewPager顶部的Tab栏，指示当前的page
     */
    private TextView tabGuideDetailInfo;
    private TextView tabGuideEvaluation;
    private TextView tabGuideHistoryGroup;

    private ViewPager myViewPager;

    private ImageView guideImage;

    private List<NameValuePair> params = new ArrayList<NameValuePair>();

    private Thread getDetailGuidesThread;

    private DetailGuideInfo guideInfo;

    public static String guideNumID;
    public static String guideName;
    public static String guideWorkAge;
    public static String guideSex;
    public static String guideLanguage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_main_page);

        setTopBar("导游详细信息");

        /**从上一个activity获取导游的身份证信息*/
        guideNumID = getIntent().getStringExtra("guideNumID");

        /**从服务端获取导游的所有信息*/
        guideInfo = getGuideFromServer(guideNumID);

        guideName = guideInfo.getGuideName();
        guideWorkAge = guideInfo.getGuideWorkAge();
        guideLanguage = guideInfo.getGuideLanguage();
        guideSex = guideInfo.getGuideSex();


        initView();

        changeImageAndTab(guideInfo);

    }


    /**
     * 根据导游的信息，修改头像和tab栏的文字
     * @param guideInfo
     */
    public void changeImageAndTab(DetailGuideInfo guideInfo){

        String sex = guideInfo.getGuideSex();

        /**根据导游的性别，修改tab栏*/
        if(sex.equals("男")){
            tabGuideDetailInfo.setText("他的信息");
            tabGuideEvaluation.setText("他的评价");
            tabGuideHistoryGroup.setText("他的团");
        }else if(sex.equals("女")){
            tabGuideDetailInfo.setText("她的信息");
            tabGuideEvaluation.setText("她的评价");
            tabGuideHistoryGroup.setText("她的团");
        }

        /**设置导游的头像*/
        guideImage = (ImageView)findViewById(R.id.guide_image);
        guideImage.setBackground(guideInfo.getGuideImage());
    }

    /**
     * 从服务端获取导游的所有信息
     */
    public DetailGuideInfo getGuideFromServer(String ID){

        NameValuePair pair = new BasicNameValuePair("guideNumID", ID);
        params.add(pair);

        getDetailGuidesThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Message msg = new Message();
//                GetDetailGuideInfoService service = new GetDetailGuideInfoService();

//                guideInfo = service.getDetailGuideInfo(params);

                String url = HttpUtils.BASE_URL + "/getGuideNumID.do";
                String result = HttpUtils.queryStringForPost(url, params);

                guideInfo = guideInfoJsonTool(result);

                if(guideInfo != null){
                    msg.what = 1;
                }
                handler.sendMessage(msg);
            }
        });

        getDetailGuidesThread.start();

        try {
            getDetailGuidesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return guideInfo;
    }

    private Handler handler = new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e("detail info",  "get detail guide info ok!");
                    break;
                default:
                    break;
            }
        }
    };

    public void initView(){

        myViewPager = (ViewPager)findViewById(R.id.guideInfoViewPager);

        tabGuideDetailInfo = (TextView)findViewById(R.id.guide_detail_info_txt);
        tabGuideEvaluation = (TextView)findViewById(R.id.guide_evaluation_txt);
        tabGuideHistoryGroup = (TextView)findViewById(R.id.guide_history_group_txt);

        GuideDetailInfoFragment guideDetailInfoFragment = new GuideDetailInfoFragment();

        Bundle bundle = new Bundle();
        bundle.putString("key", guideInfo.getGuideNumID());
        guideDetailInfoFragment.setArguments(bundle);

        GuideEvaluationFragment guideEvaluationFragment = new GuideEvaluationFragment();
        GuideHistoryGroupFragment guideHistoryGroupFragment = new GuideHistoryGroupFragment();

        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(guideDetailInfoFragment);
        fragmentList.add(guideEvaluationFragment);
        fragmentList.add(guideHistoryGroupFragment);

        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(
                getSupportFragmentManager(), fragmentList);
        myViewPager.setAdapter(myFragmentAdapter);

        myViewPager.setCurrentItem(0);

        tabGuideDetailInfo.setOnClickListener(this);
        tabGuideEvaluation.setOnClickListener(this);
        tabGuideHistoryGroup.setOnClickListener(this);

        myViewPager.setOnPageChangeListener(this);
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        switch (position){

            case 0:
                tabGuideDetailInfo.setTextColor(Color.WHITE);
                tabGuideDetailInfo.setBackgroundColor(getResources().getColor(R.color.colorDarkBlue));
                tabGuideEvaluation.setTextColor(Color.BLACK);
                tabGuideEvaluation.setBackgroundColor(Color.WHITE);
                tabGuideHistoryGroup.setTextColor(Color.BLACK);
                tabGuideHistoryGroup.setBackgroundColor(Color.WHITE);
                break;
            case 1:
                tabGuideDetailInfo.setTextColor(Color.BLACK);
                tabGuideDetailInfo.setBackgroundColor(Color.WHITE);
                tabGuideEvaluation.setTextColor(Color.WHITE);
                tabGuideEvaluation.setBackgroundColor(getResources().getColor(R.color.colorDarkBlue));
                tabGuideHistoryGroup.setTextColor(Color.BLACK);
                tabGuideHistoryGroup.setBackgroundColor(Color.WHITE);
                break;
            case 2:
                tabGuideDetailInfo.setTextColor(Color.BLACK);
                tabGuideDetailInfo.setBackgroundColor(Color.WHITE);
                tabGuideEvaluation.setTextColor(Color.BLACK);
                tabGuideEvaluation.setBackgroundColor(Color.WHITE);
                tabGuideHistoryGroup.setTextColor(Color.WHITE);
                tabGuideHistoryGroup.setBackgroundColor(getResources().getColor(R.color.colorDarkBlue));
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.guide_detail_info_txt:
                myViewPager.setCurrentItem(0);
                break;
            case R.id.guide_evaluation_txt:
                myViewPager.setCurrentItem(1);
                break;
            case R.id.guide_history_group_txt:
                myViewPager.setCurrentItem(2);
                break;
            default:
                break;
        }

    }

    /**
     * 解析服务端返回的数据
     * @param str
     */
    public DetailGuideInfo guideInfoJsonTool(String str){

        ImageTools imageTools = new ImageTools();
        byte[] byteImage = null;

        try {

            JSONObject jsonObject = new JSONObject(str);

            String guideImagePath = jsonObject.getString("guideImage");
            String guideName = jsonObject.getString("guideName");
            String guideWorkAge = jsonObject.getString("guideWorkAge");
            String guideIntro = jsonObject.getString("guideIntro");
            String guidePrice = jsonObject.getString("guidePrice");
            String guideNumID = jsonObject.getString("guideNumID");
            String guideLevel = jsonObject.getString("guideLevel");
            String guideSex = jsonObject.getString("guideSex");
            String guideAge = jsonObject.getString("guideAge");
            String guideLanguage = jsonObject.getString("guideLanguage");
            String guideCertificateID = jsonObject.getString("guideCertificateID");

            try {
                byteImage = imageTools.getImage(guideImagePath); //将网络图片转为二进制格式
            } catch (Exception e) {
                e.printStackTrace();
            }

            Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0,
                    byteImage.length);//生成位图
            bitmap = imageTools.settingImage(bitmap);
            Drawable guideImage = new BitmapDrawable(bitmap);

            guideInfo = new DetailGuideInfo(guideAge, guideSex, guideLevel,
                    guideCertificateID, guideImage, guideName, guideWorkAge, guideIntro,
                    guidePrice, guideNumID, guideLanguage);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return guideInfo;
    }

}
