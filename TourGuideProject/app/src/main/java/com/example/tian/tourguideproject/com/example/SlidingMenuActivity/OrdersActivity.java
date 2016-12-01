package com.example.tian.tourguideproject.com.example.SlidingMenuActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.SlidingMenuFragment.AllOrdersFragment;
import com.example.tian.tourguideproject.com.example.SlidingMenuFragment.BeConfirmOrdersFragment;
import com.example.tian.tourguideproject.com.example.SlidingMenuFragment.BeDealOrdersFragment;
import com.example.tian.tourguideproject.com.example.SlidingMenuFragment.BeEvaluateOrdersFragment;
import com.example.tian.tourguideproject.com.example.adapter.MyFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tian on 2016/11/22.
 */

public class OrdersActivity extends FragmentActivity implements
        ViewPager.OnPageChangeListener, View.OnClickListener {

    /*
    ViewPager包含的页面
    pageAllOrders, 全部订单的页面
    pageBeDeal， 待接单的页面
    pageBeConfirm, 待确认的页面
    pagepageBeEvaluate，待评价的页面
     */
    private View pageAllOrders;
    private View pageBeDeal;
    private View pageBeConfirm;
    private View pagepageBeEvaluate;

    /*
    ViewPager顶部的Tab栏，指示当前的page
     */
    private TextView tabAllOrders;
    private TextView tabBeDeal;
    private TextView tabBeConfirm;
    private TextView tabBeEvaluate;

    // ViewPager包含的页面列表，一般给adapter传的是一个list
    private List<View> pageList;

    private ViewPager myViewPager;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_viewpager);

        setTopBar("我的订单");

        initView();
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

    public void initView(){

        myViewPager = (ViewPager)findViewById(R.id.ordersViewPager);

        tabAllOrders = (TextView)findViewById(R.id.tab_all_orders);
        tabBeDeal = (TextView)findViewById(R.id.tab_be_deal_orders);
        tabBeConfirm = (TextView)findViewById(R.id.tab_be_confirm_orders);
        tabBeEvaluate = (TextView)findViewById(R.id.tab_be_evaluate_orders);

        AllOrdersFragment allOrdersFragment = new AllOrdersFragment();
        BeDealOrdersFragment beDealOrdersFragment = new BeDealOrdersFragment();
        BeConfirmOrdersFragment beConfirmOrdersFragment = new BeConfirmOrdersFragment();
        BeEvaluateOrdersFragment beEvaluateOrdersFragment = new BeEvaluateOrdersFragment();

        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(allOrdersFragment);
        fragmentList.add(beDealOrdersFragment);
        fragmentList.add(beConfirmOrdersFragment);
        fragmentList.add(beEvaluateOrdersFragment);

        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(
                getSupportFragmentManager(), fragmentList);
        myViewPager.setAdapter(myFragmentAdapter);

        myViewPager.setCurrentItem(0);
        tabAllOrders.setTextColor(Color.RED);
        tabBeDeal.setTextColor(Color.BLACK);
        tabBeConfirm.setTextColor(Color.BLACK);
        tabBeEvaluate.setTextColor(Color.BLACK);

        tabAllOrders.setOnClickListener(this);
        tabBeDeal.setOnClickListener(this);
        tabBeConfirm.setOnClickListener(this);
        tabBeEvaluate.setOnClickListener(this);

        myViewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        switch (position) {
            case 0:
                tabAllOrders.setTextColor(Color.RED);
                tabBeDeal.setTextColor(Color.BLACK);
                tabBeConfirm.setTextColor(Color.BLACK);
                tabBeEvaluate.setTextColor(Color.BLACK);
                break;
            case 1:
                tabAllOrders.setTextColor(Color.BLACK);
                tabBeDeal.setTextColor(Color.RED);
                tabBeConfirm.setTextColor(Color.BLACK);
                tabBeEvaluate.setTextColor(Color.BLACK);
                break;
            case 2:
                tabAllOrders.setTextColor(Color.BLACK);
                tabBeDeal.setTextColor(Color.BLACK);
                tabBeConfirm.setTextColor(Color.RED);
                tabBeEvaluate.setTextColor(Color.BLACK);
                break;
            case 3:
                tabAllOrders.setTextColor(Color.BLACK);
                tabBeDeal.setTextColor(Color.BLACK);
                tabBeConfirm.setTextColor(Color.BLACK);
                tabBeEvaluate.setTextColor(Color.RED);
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

        switch (v.getId()) {
            case R.id.tab_all_orders:
                myViewPager.setCurrentItem(0);
                break;
            case R.id.tab_be_deal_orders:
                myViewPager.setCurrentItem(1);
                break;
            case R.id.tab_be_confirm_orders:
                myViewPager.setCurrentItem(2);
                break;
            case R.id.tab_be_evaluate_orders:
                myViewPager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }
}
