package com.example.tian.tourguideproject.com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.bean.SimpleUserInfoListItem;

import java.util.List;

/**
 * Created by tian on 2016/11/21.
 */

public class UserInfoListAdapter extends BaseAdapter {

    private SimpleUserInfoListItem userinfo;
    private LayoutInflater inflater;
    private List<SimpleUserInfoListItem> item;

    private int resourceId;

    public UserInfoListAdapter(Context context, int id, List<SimpleUserInfoListItem> data) {
        this.inflater = LayoutInflater.from(context);
        this.item = data;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public SimpleUserInfoListItem getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        userinfo = getItem(position);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.userinfo_list_item, null);
        }

        TextView userinfo_name_item = (TextView)convertView.findViewById(R.id.userinfo_name_item);
        TextView userinfo_value_item = (TextView)convertView.findViewById(R.id.userinfo_value_item);
        ImageView userinfo_image_item = (ImageView)convertView.findViewById(R.id.userinfo_image_item);

        userinfo_name_item.setText(userinfo.getUserinfo_name());
        userinfo_value_item.setText(userinfo.getUserinfo_value());
        userinfo_image_item.setBackground(userinfo.getImage());

        return convertView;
    }
}
