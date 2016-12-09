package com.example.tian.tourguideproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.example.tian.tourguideproject.com.example.Activity.FindGuideActivity;
import com.example.tian.tourguideproject.com.example.Fragments.MapFragment;
import com.example.tian.tourguideproject.com.example.Activity.ReleaseOrderActivity;
import com.example.tian.tourguideproject.com.example.SlidingMenuActivity.MapTrackActivity;
import com.example.tian.tourguideproject.com.example.SlidingMenuActivity.OrdersActivity;
import com.example.tian.tourguideproject.com.example.SlidingMenuActivity.SettingActivity;
import com.example.tian.tourguideproject.com.example.SlidingMenuActivity.UserInfoActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private long mBackTime;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private CharSequence mTitle;


    private TextView barReserveTxt;
    private TextView barReleaseOrderTxt;

    private  Fragment currentFragment = null;



    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
    // The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
    private ActionBarDrawerToggle toggle;

    public static MainActivity mainActivity;

    //定义手机号为全局变量
    public static String usertel = "15029319152";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        mainActivity = this;

        OpenDrawer();

        /**
         * 顶部bar中的 “预约”
         */
        barReserveTxt = (TextView)findViewById(R.id.app_bar_reserve);
        barReserveTxt.setOnClickListener(this);

        /**
         * 顶部bar中的发布订单
         */
        barReleaseOrderTxt = (TextView)findViewById(R.id.app_bar_release_order);
        barReleaseOrderTxt.setOnClickListener(this);

        //添加地图
        MapFragment mapFragment = new MapFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_fragment,mapFragment);
        fragmentTransaction.commit();
    }

    /**
     *  打开侧滑栏
     */
    public void OpenDrawer(){

        mTitle = getTitle();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //该方法会将工具栏设置为 Activity 的应用栏
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //禁止手势滑动，只能通过点击图标打开侧滑栏，点击空白处关闭侧滑栏
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        //ActionBarDrawerToggle是一个开关，用于打开/关闭DrawerLayout抽屉
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //以下是设置标题栏的图标操作
        toggle.setDrawerIndicatorEnabled(false);//设置为false时显示自己设置的图标
        toggle.setHomeAsUpIndicator(R.drawable.ic_account_black);//自定义图标
        //点击导航栏图标以打开侧滑栏
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 处理侧滑栏条目中的点击事件
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_user_info) {

            Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_orders) {

            Intent intent = new Intent(MainActivity.this, OrdersActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tour) {

            Intent intent = new Intent(MainActivity.this, MapTrackActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_service) {

        } else if(id == R.id.nav_manage){
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.app_bar_reserve:
                /**顶部bar中的 “预约”*/
                barReserveTxt.setTextColor(Color.RED);
                barReleaseOrderTxt.setTextColor(Color.BLACK);

                Intent intent = new Intent(MainActivity.this, FindGuideActivity.class);
                startActivity(intent);

                break;
            case R.id.app_bar_release_order:
                /**顶部bar中的发布订单*/
                barReleaseOrderTxt.setTextColor(Color.RED);
                barReserveTxt.setTextColor(Color.BLACK);

                Intent intentRelaseOrder = new Intent(MainActivity.this, ReleaseOrderActivity.class);
                startActivity(intentRelaseOrder);

                break;
            default:
                break;
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mBackTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mBackTime = System.currentTimeMillis();

                getFragmentManager().popBackStack();
            } else {
                this.finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
