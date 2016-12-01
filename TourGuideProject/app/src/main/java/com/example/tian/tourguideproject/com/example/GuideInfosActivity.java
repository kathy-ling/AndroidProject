package com.example.tian.tourguideproject.com.example;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tian.tourguideproject.MainActivity;
import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.Fragments.GuideDetailInfoFragment;
import com.example.tian.tourguideproject.com.example.Fragments.GuideEvaluationFragment;
import com.example.tian.tourguideproject.com.example.Fragments.GuideHistoryGroupFragment;
import com.example.tian.tourguideproject.com.example.Fragments.ReserveGuideFragment;
import com.example.tian.tourguideproject.com.example.adapter.MyFragmentAdapter;
import com.example.tian.tourguideproject.com.example.bean.SimpleGuideInfoListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tian on 2016/11/28.
 * 导游的详细信息
 * 由她的信息、他的评价、他的团三个可滑动页面组成
 */

public class GuideInfosActivity extends FragmentActivity implements
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

    private String NumID;
    private SimpleGuideInfoListItem simpleGuideInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_main_page);

//        Intent intent = getIntent();
//        String name = intent.getStringExtra("name");
//        simpleGuideInfo = (SimpleGuideInfoListItem)intent.getSerializableExtra("guideInfo");
//        String name = simpleGuideInfo.getGuideName();

//        Toast.makeText(GuideInfosActivity.this, name, Toast.LENGTH_LONG).show();

        setTopBar("导游详细信息");

        initView();
    }

    public void initView(){

        myViewPager = (ViewPager)findViewById(R.id.guideInfoViewPager);

        tabGuideDetailInfo = (TextView)findViewById(R.id.guide_detail_info_txt);
        tabGuideEvaluation = (TextView)findViewById(R.id.guide_evaluation_txt);
        tabGuideHistoryGroup = (TextView)findViewById(R.id.guide_history_group_txt);

        GuideDetailInfoFragment guideDetailInfoFragment = new GuideDetailInfoFragment();
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

}
