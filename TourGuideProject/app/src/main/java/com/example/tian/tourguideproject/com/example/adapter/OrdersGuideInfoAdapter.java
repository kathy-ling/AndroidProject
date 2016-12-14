package com.example.tian.tourguideproject.com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.bean.OrdersMainInfoListItem;

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

    private static final String bePay = "2";  //待付款
    private static final String beHandle = "3";  //待接单
    private static final String beConfirm = "4";  //待确认
    private static final String beEvaluate = "5";  //待评价

    private ViewHolder viewHolder;

    /**
     * 所有listview的item共用同一个 mCallback
     */
    private Callback mCallback;

    /**
     * 自定义接口，用于回调按钮点击事件到Activity
     */
    public interface Callback{
        public void btnclickInListView(View view);
    }

    /**
     * 响应按钮点击事件,调用子定义接口，并传入View
     * @param view
     */
    @Override
    public void onClick(View view) {
        mCallback.btnclickInListView(view);
    }

    public OrdersGuideInfoAdapter(Context context, int id, List<OrdersMainInfoListItem> data,
                                  Callback callBack) {
        this.inflater = LayoutInflater.from(context);
        this.item = data;
        mCallback = callBack;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ordersMainInfo = getItem(i);

        if(convertView == null){

            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.page_all_orders_item, null);

            viewHolder.ordersMainNumId = (TextView) convertView.findViewById(R.id.orders_main_num_id);
            viewHolder.ordersVisitorsNum = (TextView) convertView.findViewById(R.id.orders_visitors_num);
            viewHolder.ordersVisitorsTime = (TextView) convertView.findViewById(R.id.orders_visitors_time);
            viewHolder.ordersMoney = (TextView) convertView.findViewById(R.id.orders_money);
            viewHolder.ordersStatus = (TextView) convertView.findViewById(R.id.orders_state);
            viewHolder.ordersStatusChanged = (TextView) convertView.findViewById(R.id.orders_state_change);

            convertView.setTag(viewHolder);
        }


        /**根据服务器返回的数据修改订单信息*/
        viewHolder.ordersMainNumId.setText(ordersMainInfo.getOrdersID());
        viewHolder.ordersVisitorsNum.setText(ordersMainInfo.getOrdersVisitorsNum());
        viewHolder.ordersVisitorsTime.setText(ordersMainInfo.getOrdersVisitorsTime());

        /**根据服务器返回的信息修改订单状态*/
        String status = ordersMainInfo.getOrdersStatus();
        if(status.equals(bePay))
        {
            viewHolder.ordersStatus.setText("待付款");
            viewHolder.ordersStatusChanged.setText("付款");
        }
        else if(status.equals(beHandle))
        {
            viewHolder.ordersStatus.setText("待接单");
            viewHolder.ordersStatusChanged.setText("查看");
        }
        else if(status.equals(beConfirm))
        {
            viewHolder.ordersStatus.setText("待确定");
            viewHolder.ordersStatusChanged.setText("确定");
        }
        else if(status.equals(beEvaluate))
        {
            viewHolder.ordersStatus.setText("待评价");
            viewHolder.ordersStatusChanged.setText("评价");
        }

        /**设置按钮的点击事件*/
        viewHolder.ordersStatusChanged.setOnClickListener(this);

        /**设置当前按钮所在的位置*/
        viewHolder.ordersStatusChanged.setTag(i);

        return convertView;
    }

    private class ViewHolder {

        TextView ordersMainNumId; //订单编号
        TextView ordersVisitorsNum;  //参观人数
        TextView ordersVisitorsTime;  //参观时间
        TextView ordersMoney;   //订单总额/预算
        TextView ordersStatus;    //订单状态
        TextView ordersStatusChanged;  //根据订单状态提示相应的操作（待付款--付款）
    }
}
