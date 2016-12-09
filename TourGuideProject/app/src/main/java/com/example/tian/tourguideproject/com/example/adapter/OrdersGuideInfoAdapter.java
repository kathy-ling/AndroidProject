package com.example.tian.tourguideproject.com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.SlidingMenuActivity.OrdersActivity;
import com.example.tian.tourguideproject.com.example.bean.OrdersMainInfoListItem;
import com.example.tian.tourguideproject.com.example.bean.SimpleGuideInfoListItem;

import java.util.List;

/**
 * Created by GaoXixi on 2016/12/7.
 */

public class OrdersGuideInfoAdapter extends BaseAdapter implements View.OnClickListener {

    private OrdersMainInfoListItem ordersGuideInfo;
    private LayoutInflater inflater;
    private List<OrdersMainInfoListItem> item;

    public OrdersGuideInfoAdapter(Context context, int id, List<OrdersMainInfoListItem> data) {
        this.inflater = LayoutInflater.from(context);
        this.item = data;
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public OrdersMainInfoListItem getItem(int i) {
        return item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ordersGuideInfo = getItem(i);

        if(view == null){
            view = inflater.inflate(R.layout.page_all_orders_item, null);
        }
        TextView ordersVisitorsNum = (TextView) view.findViewById(R.id.orders_visitors_num);
        TextView ordersVisitorsTime = (TextView) view.findViewById(R.id.orders_visitors_time);
        TextView ordersMoney = (TextView) view.findViewById(R.id.orders_money);

        ordersVisitorsNum.setText(ordersGuideInfo.getOrdersVisitorsNum());
        ordersVisitorsTime.setText(ordersGuideInfo.getOrdersVisitorsTime());
        ordersMoney.setText(ordersGuideInfo.getOrdersMoney());
        return view;
    }
}
