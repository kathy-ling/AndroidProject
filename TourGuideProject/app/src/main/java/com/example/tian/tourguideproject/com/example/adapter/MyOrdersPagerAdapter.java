package com.example.tian.tourguideproject.com.example.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by tian on 2016/11/23.
 *
 * PagerAdapter是用于“将多个页面填充到ViewPager”的适配器的一个基类
 */

public class MyOrdersPagerAdapter extends PagerAdapter {

    private List<View> pageList;

    public MyOrdersPagerAdapter(List<View> pageList) {
        this.pageList = pageList;
    }

    @Override
    public int getCount() {
        return pageList.size(); //返回要展示的页面的数量
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {

        // 将当前位置的View移除
        container.removeView(pageList.get(position));
    }

    //负责初始化指定位置的页面，并且需要返回当前页面本身
    @Override public Object instantiateItem(ViewGroup container, int position) {

        //每次滑动的时候把视图添加到viewpager
        container.addView(pageList.get(position));
        return pageList.get(position);
    }
}
