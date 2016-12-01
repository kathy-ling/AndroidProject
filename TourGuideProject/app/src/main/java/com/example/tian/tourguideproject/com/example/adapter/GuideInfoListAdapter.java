package com.example.tian.tourguideproject.com.example.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tian.tourguideproject.MainActivity;
import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.GuideInfosActivity;
import com.example.tian.tourguideproject.com.example.bean.SimpleGuideInfoListItem;

import java.util.List;

/**
 * Created by tian on 2016/11/25.
 */

public class GuideInfoListAdapter extends BaseAdapter implements View.OnClickListener{

    private SimpleGuideInfoListItem guideInfo;
    private LayoutInflater inflater;
    private List<SimpleGuideInfoListItem> item;

    private String NumID = null;
    private String Name = null;


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
        TextView guideNumID = (TextView)convertView.findViewById(R.id.guide_NumID_list_item);

        guideImageItem.setBackground(guideInfo.getGuideImage());
        guideNameItem.setText(guideInfo.getGuideName());
        guideWorkAgeItem.setText(guideInfo.getGuideWorkAge());
        guideIntroItem.setText(guideInfo.getGuideIntro());
        guidePriceItem.setText(guideInfo.getGuidePrice());
        guideNumID.setText(guideInfo.getGuideNumID());

        NumID = guideInfo.getGuideNumID();
        Name = guideInfo.getGuideName();

//        guideImageItem.setOnClickListener(this);
//        guideImageItem.setOnClickListener(this);
//        TextView check = (TextView)convertView.findViewById(R.id.check_btn_guide_list_item);
//        check.setOnClickListener(this);
//        LinearLayout checkLayout = (LinearLayout)convertView.findViewById(R.id.check_layout);
//        checkLayout.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            /**点击查看导游的详细信息*/
            case R.id.guide_image_list_item:
            case R.id.guide_name_list_item:
            case R.id.check_btn_guide_list_item:
            case R.id.check_layout:

                Toast.makeText(MainActivity.mainActivity,
                        guideInfo.getGuideNumID() + guideInfo.getGuideName() + Name + NumID,
                        Toast.LENGTH_LONG).show();

//                Toast.makeText(MainActivity.mainActivity, guideInfo.getGuideName(), Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(MainActivity.mainActivity, GuideInfosActivity.class);
////                intent.putExtra("NumID", NumID);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("guideInfo", guideInfo);
//                intent.putExtras(bundle);
//                MainActivity.mainActivity.startActivity(intent);
                break;
        }
    }
}
