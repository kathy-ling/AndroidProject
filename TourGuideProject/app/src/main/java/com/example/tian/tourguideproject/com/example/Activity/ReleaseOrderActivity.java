package com.example.tian.tourguideproject.com.example.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.utils.HttpUtils;
import com.example.tian.tourguideproject.com.example.utils.JsonTools;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tian on 2016/12/5.
 */

public class ReleaseOrderActivity extends Activity implements View.OnClickListener{

    private LinearLayout chooseTimeLayout;

    private EditText timeTxt;
    private EditText personNumTxt;
    private EditText telTxt;
    private EditText budgetTxt;
    private EditText otherCommand;

    private Button releaseOrder;

    private Thread releaseOrderThread;

    private List<NameValuePair> params = new ArrayList<NameValuePair>();

    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_order_page);

        setTopBar("发布订单");

        initView();
    }

    /**
     * 设置TopBar的标题和返回按钮
     */
    public void setTopBar(String title){

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

    public void initView(){

        chooseTimeLayout = (LinearLayout)findViewById(R.id.choose_time_layout);

        timeTxt = (EditText)findViewById(R.id.time_txt);
        personNumTxt = (EditText)findViewById(R.id.person_num_txt);
        telTxt = (EditText)findViewById(R.id.tel_txt);
        budgetTxt = (EditText)findViewById(R.id.budget_txt);
        otherCommand = (EditText)findViewById(R.id.other_command);

        releaseOrder = (Button)findViewById(R.id.release_order_btn);

        timeTxt.setOnClickListener(this);
        chooseTimeLayout.setOnClickListener(this);
        releaseOrder.setOnClickListener(this);
    }

    public List<NameValuePair> getViewAndParams(){

        String time = timeTxt.getText().toString();
        String num = personNumTxt.getText().toString();
        String orderphone = telTxt.getText().toString();
        String budget = budgetTxt.getText().toString();
        String command = otherCommand.getText().toString();

        NameValuePair pair = new BasicNameValuePair("visitDate" ,time);
        NameValuePair pair1 = new BasicNameValuePair("orderphone", orderphone);
        NameValuePair pair2 = new BasicNameValuePair("budget", budget);
        NameValuePair pair3 = new BasicNameValuePair("otherCommand", command);
        NameValuePair pair4 = new BasicNameValuePair("personNum", num);
        NameValuePair pair5 = new BasicNameValuePair("Tel", "15029319152");

        params.add(pair);
        params.add(pair1);
        params.add(pair2);
        params.add(pair3);
        params.add(pair4);
        params.add(pair5);

        return params;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.time_txt:
            case R.id.choose_time_layout:

                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        timeTxt.setText(DateFormat.format("yyy-MM-dd", calendar));
                    }
                };

                DatePickerDialog dialog = new DatePickerDialog(ReleaseOrderActivity.this,listener,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();

                break;
            case R.id.release_order_btn:

                ReleaseOrder();
                break;
        }
    }

    public void ReleaseOrder(){

        releaseOrderThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Message msg = new Message();
                params = getViewAndParams();

                String url = HttpUtils.BASE_URL + "/UpdateRealeaseOfvisitor.do";
                String result = HttpUtils.queryStringForPost(url, params);

                //解析获得的数据
                String ret = JsonTools.releaseInfoJsonTool(result);


                if(ret.equals("1")){
                    msg.what = 1;
                }else if(ret.equals("0")){
                    msg.what = 2;
                }
                handler.sendMessage(msg);
            }
        });

        releaseOrderThread.start();
    }

    private Handler handler = new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e("release", "release order OK!");
                    Toast.makeText(ReleaseOrderActivity.this,
                        "订单发布成功！", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Log.e("release", "release order failed!");
                    break;
                default:
                    break;
            }
        }
    };


}
