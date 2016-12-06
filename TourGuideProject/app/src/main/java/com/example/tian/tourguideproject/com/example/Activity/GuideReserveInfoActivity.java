package com.example.tian.tourguideproject.com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;

/**
 * Created by tian on 2016/11/29.
 * 选定导游后，进行下单预约
 */

public class GuideReserveInfoActivity extends Activity {

    private TextView nameTxt;
    private TextView sexTxt;
    private TextView languageAndWorkAge;

    private EditText personNum;
    private EditText personName;
    private EditText personTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_info_page);

        setTopBar("导游姓名");

        initView();

        setView();
    }

    /*
    设置TopBar的标题和返回按钮
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

        nameTxt = (TextView)findViewById(R.id.reserve_guide_name);
        sexTxt = (TextView)findViewById(R.id.reserve_guide_sex);
        languageAndWorkAge = (TextView)findViewById(R.id.reserve_language_workage);

        personName = (EditText)findViewById(R.id.reserve_person_name);
        personNum = (EditText)findViewById(R.id.reserve_person_num);
        personTel = (EditText)findViewById(R.id.reserve_person_tel);
    }

    public void setView(){

        nameTxt.setText(GuideDetailInfosActivity.guideName);
        sexTxt.setText(" (" + GuideDetailInfosActivity.guideSex + ")");
        languageAndWorkAge.setText(GuideDetailInfosActivity.guideLanguage
                + " | " + GuideDetailInfosActivity.guideWorkAge);
    }
}
