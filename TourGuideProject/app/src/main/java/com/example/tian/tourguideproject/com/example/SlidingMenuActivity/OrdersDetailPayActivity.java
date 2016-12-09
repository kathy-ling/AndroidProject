package com.example.tian.tourguideproject.com.example.SlidingMenuActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;

public class OrdersDetailPayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_detail_pay_info);

        setTopBar("订单详细信息");
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
