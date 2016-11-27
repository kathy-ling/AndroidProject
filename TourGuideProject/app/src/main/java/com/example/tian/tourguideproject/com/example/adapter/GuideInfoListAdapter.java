package com.example.tian.tourguideproject.com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.bean.SimpleGuideInfoListItem;

import java.util.List;

/**
 * Created by tian on 2016/11/25.
 */

public class GuideInfoListAdapter extends BaseAdapter {

    private SimpleGuideInfoListItem guideInfo;
    private LayoutInflater inflater;
    private List<SimpleGuideInfoListItem> item;

    public GuideInfoListAdapter(SimpleGuideInfoListItem guideInfo,
                                LayoutInflater inflater, List<SimpleGuideInfoListItem> item) {
        this.guideInfo = guideInfo;
        this.inflater = inflater;
        this.item = item;
    }

    public GuideInfoListAdapter(Context context, int id, List<SimpleGuideInfoListItem> data) {
        this.inflater = LayoutInflater.from(context);
        this.item = data;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public SimpleGuideInfoListItem getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        guideInfo = getItem(position);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.guide_reserve_guide_list_item, null);
        }

        ImageView guideImageItem = (ImageView)convertView.findViewById(R.id.guide_image_list_item);
        TextView guideNameItem = (TextView)convertView.findViewById(R.id.guide_name_list_item);
        TextView guideWorkAgeItem = (TextView)convertView.findViewById(R.id.guide_work_age_list_item);
        TextView guideIntroItem = (TextView)convertView.findViewById(R.id.guide_intro_list_item);
        TextView guidePriceItem = (TextView)convertView.findViewById(R.id.guide_price_list_item);

        guideImageItem.setBackground(guideInfo.getGuideImage());
        guideNameItem.setText(guideInfo.getGuideName());
        guideWorkAgeItem.setText(guideInfo.getGuideWorkAge());
        guideIntroItem.setText(guideInfo.getGuideIntro());
        guidePriceItem.setText(guideInfo.getGuidePrice());


        return convertView;
    }
}
