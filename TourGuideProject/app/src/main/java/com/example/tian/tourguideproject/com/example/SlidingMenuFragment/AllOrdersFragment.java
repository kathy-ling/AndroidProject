package com.example.tian.tourguideproject.com.example.SlidingMenuFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.SlidingMenuActivity.OrdersActivity;
import com.example.tian.tourguideproject.com.example.SlidingMenuActivity.OrdersDetailPayActivity;
import com.example.tian.tourguideproject.com.example.adapter.OrdersGuideInfoAdapter;
import com.example.tian.tourguideproject.com.example.bean.OrdersMainInfoListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tian on 2016/11/23.
 */

public class AllOrdersFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private List<OrdersMainInfoListItem> ordersMainInfoList = new ArrayList<>();

    private TextView ordersID;
    private TextView visitorNum;
    private TextView visitortime;
    private TextView ordersMoney;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.page_all_orders, null);

        initView(view);

        /**获得OrderActivity中的全部订单信息*/
        OrdersActivity ordersActivity = new OrdersActivity();
        ordersMainInfoList = ordersActivity.getOrdersMainInfo();

        /**为listview传值*/
        listView = (ListView) view.findViewById(R.id.orders_all_listview);
        OrdersGuideInfoAdapter adapter = new OrdersGuideInfoAdapter(this.getActivity(),
                R.layout.page_all_orders,ordersMainInfoList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    /**实例化控件*/
    private void initView(View view) {
        ordersID = (TextView) view.findViewById(R.id.orders_main_num_id);
        visitorNum = (TextView) view.findViewById(R.id.orders_visitors_num);
        visitortime = (TextView) view.findViewById(R.id.orders_visitors_time);
        ordersMoney = (TextView) view.findViewById(R.id.orders_money);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        OrdersMainInfoListItem ordersMainInfo  = ordersMainInfoList.get(i);

        Intent intent = new Intent(getActivity(),OrdersDetailPayActivity.class);
        startActivity(intent);

    }
}
