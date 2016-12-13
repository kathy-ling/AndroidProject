package com.example.tian.tourguideproject.com.example.SlidingMenuActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;

public class OrdersDetailPayActivity extends AppCompatActivity {

    private TextView ordersUserName;   //预定人姓名
    private TextView ordersUserTel;    //预定人手机号
    private TextView ordersVisitorNum;  //参观人数
    private TextView ordersVisitorTime;  //参观时间
    private TextView ordersUserMoney;  //订单总额
    private TextView ordersGuideNumId;   //预定导游证号
    private TextView ordersPayId;  //订单编号
    private TextView ordersPayTime;  //下单时间
    private Button ordersCancel;   //取消订单
    private Button ordersPay;  //付款

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_detail_pay_info);
        setTopBar("订单详细信息");

        initView();

    }

    private void initView() {
        ordersUserName = (TextView) findViewById(R.id.orders_user_name);
        ordersUserTel = (TextView) findViewById(R.id.orders_user_tel);
        ordersVisitorNum = (TextView) findViewById(R.id.orders_visitors_num);
        ordersVisitorTime = (TextView) findViewById(R.id.orders_visitors_time);
        ordersUserMoney = (TextView) findViewById(R.id.orders_user_money);
        ordersGuideNumId = (TextView) findViewById(R.id.orders_guide_NumId);
        ordersPayId = (TextView) findViewById(R.id.orders_pay_time_id);
        ordersPayTime = (TextView) findViewById(R.id.orders_pay_time);
        ordersCancel = (Button) findViewById(R.id.bt_orders_pay);
        ordersPay = (Button) findViewById(R.id.bt_orders_pay);
    }

    /**
     * 设置TopBar的标题和返回按钮
     */
    private void setTopBar(String title)
    {
        ImageView back_img = (ImageView)findViewById(R.id.topbar_backkey);
        TextView topbar_title = (TextView)findViewById(R.id.topbar_title);

        topbar_title.setText(title);
        back_img.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
