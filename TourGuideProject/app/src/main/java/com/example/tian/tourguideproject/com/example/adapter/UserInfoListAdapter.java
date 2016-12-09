package com.example.tian.tourguideproject.com.example.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.bean.SimpleGuideInfoListItem;
import com.example.tian.tourguideproject.com.example.bean.SimpleUserInfoListItem;

import java.util.List;

/**
 * Created by tian on 2016/11/21.
 */

public class UserInfoListAdapter extends BaseAdapter {

    private SimpleUserInfoListItem userinfo;
    private LayoutInflater inflater;
    private List<SimpleUserInfoListItem> item;

    private ViewHolder viewHolder;

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

            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.userinfo_list_item, null);

            viewHolder.NameItem = (TextView)convertView.findViewById(R.id.userinfo_name_item);
            viewHolder.ValueItem = (TextView)convertView.findViewById(R.id.userinfo_value_item);
            viewHolder.ImageItem = (ImageView)convertView.findViewById(R.id.userinfo_image_item);

            convertView.setTag(viewHolder);
        }else{

            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.NameItem.setText(userinfo.getUserinfo_name());
        viewHolder.ValueItem.setText(userinfo.getUserinfo_value());
        viewHolder.ImageItem.setBackground(userinfo.getImage());

//        if(convertView == null){
//            convertView = inflater.inflate(R.layout.userinfo_list_item, null);
//        }
//
//        TextView userinfo_name_item = (TextView)convertView.findViewById(R.id.userinfo_name_item);
//        TextView userinfo_value_item = (TextView)convertView.findViewById(R.id.userinfo_value_item);
//        ImageView userinfo_image_item = (ImageView)convertView.findViewById(R.id.userinfo_image_item);
//
//        userinfo_name_item.setText(userinfo.getUserinfo_name());
//        userinfo_value_item.setText(userinfo.getUserinfo_value());
//        userinfo_image_item.setBackground(userinfo.getImage());

        return convertView;
    }

    private static class ViewHolder{

        TextView NameItem;
        TextView ValueItem;
        ImageView ImageItem;
    }

    public void updataView(int posi, SimpleUserInfoListItem changedValue, ListView listView) {

        int visibleFirstPosi = listView.getFirstVisiblePosition();
        int visibleLastPosi = listView.getLastVisiblePosition();
        Log.e("in update", "got to update method");

        if (posi >= visibleFirstPosi && posi <= visibleLastPosi) {
            View view = listView.getChildAt(posi - visibleFirstPosi);
            ViewHolder holder = (ViewHolder) view.getTag();

            holder.NameItem.setText(changedValue.getUserinfo_name());
            holder.ValueItem.setText(changedValue.getUserinfo_value());
            item.set(posi, changedValue);
        } else {
            SimpleUserInfoListItem userinfoItem = item.get(posi);
            item.set(posi, userinfoItem);
        }
    }
}
