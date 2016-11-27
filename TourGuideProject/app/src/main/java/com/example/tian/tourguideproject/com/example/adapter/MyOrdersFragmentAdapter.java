package com.example.tian.tourguideproject.com.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by tian on 2016/11/23.
 *
 * FragmentPagerAdapter继承自PagerAdapter ，主要用来展示多个Fragment页面，
 * 并且每一个Fragment都会被保存在fragment manager中。
 * 适用于少量且相对静态的页面
 */

public class MyOrdersFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public MyOrdersFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
