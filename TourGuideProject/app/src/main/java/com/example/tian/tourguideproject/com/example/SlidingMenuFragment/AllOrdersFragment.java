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
import android.widget.Toast;

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

public class AllOrdersFragment extends Fragment implements
        OrdersGuideInfoAdapter.Callback{

    private ListView listView;
    private List<OrdersMainInfoListItem> ordersMainInfoList = new ArrayList<>();

    private TextView ordersID;
    private TextView visitorNum;
    private TextView visitortime;
    private TextView ordersMoney;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.page_all_orders, null);

        /**获得OrderActivity中的全部订单信息*/
        OrdersActivity ordersActivity = new OrdersActivity();
        ordersMainInfoList = ordersActivity.getOrdersMainInfo();

        /**为listview传值*/
        listView = (ListView) view.findViewById(R.id.orders_all_listview);
        OrdersGuideInfoAdapter adapter = new OrdersGuideInfoAdapter(this.getActivity(),
                R.layout.page_all_orders,ordersMainInfoList, this);
        listView.setAdapter(adapter);

        return view;
    }

    /**
     * 接口方法，响应ListView内部的按钮点击事件
     * @param view
     * 跳转到相应订单的详细信息界面
     */
    @Override
    public void btnclickInListView(View view) {

//        Toast.makeText(getActivity(),
//                        "listview的内部的按钮被点击了！，位置是-->" + (Integer) view.getTag() ,
//                Toast.LENGTH_LONG).show();

        OrdersMainInfoListItem ordersMainInfo  = ordersMainInfoList.
                get((Integer) view.getTag());

        /**
         * ===================================
         * 查看对应订单的详细信息
         * 传参数：订单编号
         */
        Intent intent = new Intent(getActivity(),OrdersDetailPayActivity.class);
        startActivity(intent);

    }
}
