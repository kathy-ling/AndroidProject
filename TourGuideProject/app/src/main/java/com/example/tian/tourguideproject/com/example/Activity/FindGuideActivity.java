package com.example.tian.tourguideproject.com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.adapter.GuideInfoListAdapter;
import com.example.tian.tourguideproject.com.example.bean.SimpleGuideInfoListItem;
import com.example.tian.tourguideproject.com.example.utils.HttpUtils;
import com.example.tian.tourguideproject.com.example.utils.ImageTools;
import com.example.tian.tourguideproject.com.example.utils.JsonTools;
import com.example.tian.tourguideproject.com.example.utils.MyRadioGroup;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tian on 2016/12/1.
 * 需要实现listview中的自定义接口 Callback
 */

public class FindGuideActivity extends Activity implements
        View.OnClickListener, RadioGroup.OnCheckedChangeListener,
        GuideInfoListAdapter.Callback{

    private ListView listView;

    /**
     * 语种、性别、年龄三个RadioGroup中的RadioButton
     */
    private RadioButton allLanguageRadio;
    private RadioButton chineseRadio;
    private RadioButton englishRadio;

    private RadioButton allSexRadio;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;

    private RadioButton allAgeRadio;
    private RadioButton twentyRadio;
    private RadioButton thirtyRadio;
    private RadioButton fortyRadio;


    /**
     * 显示已选择的筛选条件
     */
    private LinearLayout haveChosenLayout;
    private LinearLayout languageChosenLayout;
    private LinearLayout ageChosenLayout;
    private LinearLayout sexChosenLayout;

    private TextView chosenConditionTxt;
    private TextView chosenLanguageTxt;
    private TextView chosenSexTxt;
    private TextView chosenAgeTxt;

    private String languageSelected;
    private String sexSelected;
    private String ageSelected;

    /**
     * 计数，满足条件的导游数
     */
    private TextView totalGuidesTxt;

    private List<SimpleGuideInfoListItem> guideInfoList = new ArrayList<>();

    private List<NameValuePair> params = new ArrayList<NameValuePair>();

    private Thread getAllGuidesThread;

    private Thread getSelectedGuidesThread;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_reserve_main);

        setTopBar("查看导游");

        totalGuidesTxt = (TextView)findViewById(R.id.guides_meet_condition);

        /**从服务端获取导游的信息*/
        guideInfoList = getAllGuidesInfo();

        /**符合条件的导游数量*/
        totalGuidesTxt.setText(guideInfoList.size()+"");

        GuideInfoListAdapter adapter = new GuideInfoListAdapter(FindGuideActivity.this,
                R.layout.guide_reserve_guide_list, guideInfoList, this); //this->callback
        listView = (ListView)findViewById(R.id.reserve_guide_listview);
        listView.setAdapter(adapter);

        initRadioGroup();

        initClearSelector();
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

    /**
     * 与服务端交互,获取所有导游信息
     */
    public List<SimpleGuideInfoListItem> getAllGuidesInfo(){

        getAllGuidesThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Message msg = new Message();

                String url = HttpUtils.BASE_URL + "/getguideinfo.do";
                String result = HttpUtils.queryStringForGet(url);

                //解析获得的数据
                guideInfoList = JsonTools.guideInfoJsonTool(result);

                Log.e(" guideInfoList", guideInfoList.size() + "");

                if(guideInfoList != null){
                    msg.what = 1;
                }
                handler.sendMessage(msg);
            }
        });

        getAllGuidesThread.start();

        try {
            getAllGuidesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return guideInfoList;
    }

    /**
     * 与服务端交互,获取满足条件的导游信息
     */
    public List<SimpleGuideInfoListItem> getSelectedGuidesInfo(final List<NameValuePair> params){

        getSelectedGuidesThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Message msg = new Message();

                String url = HttpUtils.BASE_URL + "/getSelectedGuideInfo.do";
                String result = HttpUtils.queryStringForPost(url, params);

                guideInfoList = JsonTools.guideInfoJsonTool(result);

                if(guideInfoList != null){
                    msg.what = 2;
                }else{
                    msg.what = 3;
                }
                handler.sendMessage(msg);
            }
        });

        getSelectedGuidesThread.start();

        try {
            getSelectedGuidesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return guideInfoList;
    }

    private Handler handler = new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e("guides number", guideInfoList.size() + "");
                    break;
                case 2:
                    Log.e("selected", guideInfoList.size()+"");
                    Toast.makeText(FindGuideActivity.this, "selected:" + guideInfoList.size()+"",
                            Toast.LENGTH_SHORT).show();
                case 3:
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 选择筛选条件
     */
    public void initRadioGroup(){

        RadioGroup languageRadioGroup = (RadioGroup)findViewById(R.id.language_radio_group);
        allLanguageRadio = (RadioButton)findViewById(R.id.language_all_radio);
        chineseRadio = (RadioButton)findViewById(R.id.language_chinese_radio);
        englishRadio = (RadioButton)findViewById(R.id.language_english_radio);

        RadioGroup sexRadioGroup = (RadioGroup)findViewById(R.id.sex_radio_group);
        allSexRadio = (RadioButton)findViewById(R.id.sex_all_radio);
        maleRadio = (RadioButton)findViewById(R.id.sex_male_radio);
        femaleRadio = (RadioButton)findViewById(R.id.sex_female_radio);

        MyRadioGroup ageRadioGroup = (MyRadioGroup)findViewById(R.id.age_radio_group);
        allAgeRadio = (RadioButton)findViewById(R.id.age_all_radio);
        twentyRadio = (RadioButton)findViewById(R.id.age_20_30_radio);
        thirtyRadio = (RadioButton)findViewById(R.id.age_30_40_radio);
        fortyRadio = (RadioButton)findViewById(R.id.age_40_50_radio);

        languageRadioGroup.setOnCheckedChangeListener(this);
        sexRadioGroup.setOnCheckedChangeListener(this);
        ageRadioGroup.setOnCheckedChangeListener(myRadioGroupListener);

        TextView guideSearch = (TextView)findViewById(R.id.search_guide_btn);
        guideSearch.setOnClickListener(this);
    }

    /**
     * 显示已经选择的条件
     * 删除已经选择的条件
     */
    public void initClearSelector(){

        haveChosenLayout = (LinearLayout)findViewById(R.id.have_chosen_layout);

        languageChosenLayout = (LinearLayout)findViewById(R.id.chosen_language_layout);
        ageChosenLayout = (LinearLayout)findViewById(R.id.chosen_age_layout);
        sexChosenLayout = (LinearLayout)findViewById(R.id.chosen_sex_layout);

        chosenConditionTxt = (TextView)findViewById(R.id.have_chosen_txt);
        chosenLanguageTxt = (TextView)findViewById(R.id.chosen_language_txt);
        chosenSexTxt = (TextView)findViewById(R.id.chosen_sex_txt);
        chosenAgeTxt = (TextView)findViewById(R.id.chosen_age_txt);

        //初始时，已选条件 布局隐藏
        haveChosenLayout.setVisibility(View.GONE);
        languageChosenLayout.setVisibility(View.GONE);
        ageChosenLayout.setVisibility(View.GONE);
        sexChosenLayout.setVisibility(View.GONE);

        //删除已经选择的条件
        languageChosenLayout.setOnClickListener(this);
        ageChosenLayout.setOnClickListener(this);
        sexChosenLayout.setOnClickListener(this);
    }

    /**
     * 点击查询按钮
     * 按一定格式处理当前的查询条件
     */
    public void ClickSearchButton(){

        languageSelected = chosenLanguageTxt.getText().toString();
        sexSelected = chosenSexTxt.getText().toString();
        ageSelected = chosenAgeTxt.getText().toString();

        if(languageSelected == null && sexSelected== null && ageSelected== null){
            guideInfoList = getAllGuidesInfo();
        }

        String language = null;
        String sex = null;

        if(languageSelected.equals("中文")){
            language = String.valueOf(1);
        }
        if(languageSelected.equals("英语")){
            language = String.valueOf(2);
        }
        if(sexSelected.equals("男")){
            sex = String.valueOf(0);
        }
        if(sexSelected.equals("女")){
            sex = String.valueOf(1);
        }

        NameValuePair pair = new BasicNameValuePair("languageSelected" ,language);
        NameValuePair pair1 = new BasicNameValuePair("sexSelected", sex);
        NameValuePair pair2 = new BasicNameValuePair("ageSelected", ageSelected);

        params.add(pair);
        params.add(pair1);
        params.add(pair2);

        guideInfoList = getSelectedGuidesInfo(params);

        totalGuidesTxt.setText(guideInfoList.size()+"");

        GuideInfoListAdapter adapter = new GuideInfoListAdapter(FindGuideActivity.this,
                R.layout.guide_reserve_guide_list, guideInfoList, this);
        listView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_guide_btn:
                /**点击查询按钮*/
                ClickSearchButton();
                break;
            case R.id.chosen_language_layout:
                languageChosenLayout.setVisibility(View.GONE);
                chosenLanguageTxt.setText("");
                allLanguageRadio.setChecked(true);
                break;
            case R.id.chosen_sex_layout:
                sexChosenLayout.setVisibility(View.GONE);
                chosenSexTxt.setText("");
                allSexRadio.setChecked(true);
                break;
            case R.id.chosen_age_layout:
                ageChosenLayout.setVisibility(View.GONE);
                chosenAgeTxt.setText("");
                if(twentyRadio.isChecked()){
                    twentyRadio.setChecked(false);
                }
                if(thirtyRadio.isChecked()){
                    thirtyRadio.setChecked(false);
                }
                if(fortyRadio.isChecked()){
                    fortyRadio.setChecked(false);
                }
                allAgeRadio.setChecked(true);
                break;
            default:
                break;
        }
    }

    /**
     * 处理RadioGroup的选择事件
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        int id = group.getCheckedRadioButtonId();

        switch (id){
            case R.id.language_all_radio:
                /**语种：不限*/
                if(allAgeRadio.isChecked() && allSexRadio.isChecked()){
                    haveChosenLayout.setVisibility(View.GONE);
                    chosenConditionTxt.setVisibility(View.GONE);
                }
                if(!(allAgeRadio.isChecked() && allSexRadio.isChecked())){
                    haveChosenLayout.setVisibility(View.VISIBLE);
                    chosenConditionTxt.setVisibility(View.VISIBLE);
                }
                languageChosenLayout.setVisibility(View.GONE);
                chosenLanguageTxt.setText("");
                break;

            case R.id.language_chinese_radio:
                /**语种：中文*/
                haveChosenLayout.setVisibility(View.VISIBLE);
                chosenConditionTxt.setVisibility(View.VISIBLE);
                //获取已选radioButton对应的文字
                chosenLanguageTxt.setText(chineseRadio.getText());
                languageChosenLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.language_english_radio:
                /**语种：英语*/
                haveChosenLayout.setVisibility(View.VISIBLE);
                chosenConditionTxt.setVisibility(View.VISIBLE);
                //获取已选radioButton对应的文字
                chosenLanguageTxt.setText(englishRadio.getText());
                languageChosenLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.sex_all_radio:
                /**性别：不限*/
                if(allLanguageRadio.isChecked() && allAgeRadio.isChecked()){
                    haveChosenLayout.setVisibility(View.GONE);
                    chosenConditionTxt.setVisibility(View.GONE);
                }
                if(!(allLanguageRadio.isChecked() && allAgeRadio.isChecked())){
                    haveChosenLayout.setVisibility(View.VISIBLE);
                    chosenConditionTxt.setVisibility(View.VISIBLE);
                }
                sexChosenLayout.setVisibility(View.GONE);
                chosenSexTxt.setText("");
                break;

            case R.id.sex_male_radio:
                /**性别：男*/
                haveChosenLayout.setVisibility(View.VISIBLE);
                chosenConditionTxt.setVisibility(View.VISIBLE);
                chosenSexTxt.setText(maleRadio.getText());
                sexChosenLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.sex_female_radio:
                /**性：女*/
                haveChosenLayout.setVisibility(View.VISIBLE);
                chosenConditionTxt.setVisibility(View.VISIBLE);
                chosenSexTxt.setText(femaleRadio.getText());
                sexChosenLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 自定义的RadioGroup的选择事件
     */
    RadioGroup.OnCheckedChangeListener myRadioGroupListener = new
            RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    switch (checkedId){
                        case R.id.age_all_radio:
                            /**年龄：不限*/
                            if(allLanguageRadio.isChecked() && allSexRadio.isChecked()){
                                haveChosenLayout.setVisibility(View.GONE);
                                chosenConditionTxt.setVisibility(View.GONE);
                            }
                            if(!(allLanguageRadio.isChecked() && allSexRadio.isChecked())){
                                haveChosenLayout.setVisibility(View.VISIBLE);
                                chosenConditionTxt.setVisibility(View.VISIBLE);
                            }
                            ageChosenLayout.setVisibility(View.GONE);
                            chosenAgeTxt.setText("");
                            break;

                        case R.id.age_20_30_radio:
                            /**年龄：20-30*/
                            haveChosenLayout.setVisibility(View.VISIBLE);
                            chosenConditionTxt.setVisibility(View.VISIBLE);
                            chosenAgeTxt.setText(twentyRadio.getText());
                            ageChosenLayout.setVisibility(View.VISIBLE);
                            break;

                        case R.id.age_30_40_radio:
                            /**年龄：30-40*/
                            haveChosenLayout.setVisibility(View.VISIBLE);
                            chosenConditionTxt.setVisibility(View.VISIBLE);
                            chosenAgeTxt.setText(thirtyRadio.getText());
                            ageChosenLayout.setVisibility(View.VISIBLE);
                            break;

                        case R.id.age_40_50_radio:
                            /**年龄：40-50*/
                            haveChosenLayout.setVisibility(View.VISIBLE);
                            chosenConditionTxt.setVisibility(View.VISIBLE);
                            chosenAgeTxt.setText(fortyRadio.getText());
                            ageChosenLayout.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            };


    /**
     * 接口方法，响应ListView内部的按钮点击事件
     * @param view
     * 跳转到导游的详细信息界面
     */
    @Override
    public void clickInListView(View view) {

        SimpleGuideInfoListItem guideInfo = guideInfoList.get((Integer) view.getTag());

        switch (view.getId()) {
            /**点击查看导游的详细信息*/
            case R.id.guide_image_list_item:
            case R.id.guide_name_list_item:
            case R.id.check_btn_guide_list_item:

//                Toast.makeText(FindGuideActivity.this,
//                        "listview的内部的按钮被点击了！，位置是-->" + (Integer) view.getTag() ,
//                Toast.LENGTH_LONG).show();

                //向下一个页面传入导游的身份证信息
                Intent intent = new Intent(FindGuideActivity.this, GuideDetailInfosActivity.class);
                intent.putExtra("guideNumID", guideInfo.getGuideNumID());
                startActivity(intent);
                break;
        }
    }


}
