package com.example.tian.tourguideproject.com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.SlidingMenuActivity.OrdersActivity;
import com.example.tian.tourguideproject.com.example.bean.OrdersMainInfoListItem;
import com.example.tian.tourguideproject.com.example.bean.SimpleGuideInfoListItem;

import java.util.List;

/**
 * Created by GaoXixi on 2016/12/7.
 *
 * 使用接口回调的方式处理listitem内部点击事件
 */

public class OrdersGuideInfoAdapter extends BaseAdapter implements View.OnClickListener {

    private OrdersMainInfoListItem ordersMainInfo;
    private LayoutInflater inflater;
    private List<OrdersMainInfoListItem> item;

    private ViewHolder viewHolder;
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
        ordersMainInfo = getItem(i);

        if(view == null){
            view = inflater.inflate(R.layout.page_all_orders_item, null);
        }
        TextView ordersMainNumId = (TextView) view.findViewById(R.id.orders_main_num_id);
        TextView ordersVisitorsNum = (TextView) view.findViewById(R.id.orders_visitors_num);
        TextView ordersVisitorsTime = (TextView) view.findViewById(R.id.orders_visitors_time);
        TextView ordersMoney = (TextView) view.findViewById(R.id.orders_money);
        TextView ordersStatus = (TextView) view.findViewById(R.id.orders_state);
        TextView ordersStatusChanged = (TextView) view.findViewById(R.id.orders_state_change);
        TextView ordersTorB = (TextView) view.findViewById(R.id.orders_total);

        /**根据服务器返回的数据修改订单信息*/
        ordersMainNumId.setText(ordersMainInfo.getOrdersID());
        ordersVisitorsNum.setText(ordersMainInfo.getOrdersVisitorsNum());
        ordersVisitorsTime.setText(ordersMainInfo.getOrdersVisitorsTime());
        ordersMoney.setText(ordersMainInfo.getOrdersMoney());

        /**根据服务器返回的信息修改订单状态*/
        String status = ordersMainInfo.getOrdersStatus();
        if(status.equals("2"))
        {
            ordersStatus.setText("待付款");
            ordersStatusChanged.setText("付款");
            ordersTorB.setText("订单预算");
        }
        else if(status.equals("3"))
        {
            ordersStatus.setText("待接单");
            ordersStatusChanged.setText("下单");
            ordersTorB.setText("订单预算");
        }
        else if(status.equals("4"))
        {
            ordersStatus.setText("待确定");
            ordersStatusChanged.setText("付款");
            ordersTorB.setText("订单总额");
        }
        else if(status.equals("5"))
        {
            ordersStatus.setText("待评价");
            ordersStatusChanged.setText("评价");
            ordersTorB.setText("订单总额");
        }

        return view;
    }

    private class ViewHolder
    {

    }
}
