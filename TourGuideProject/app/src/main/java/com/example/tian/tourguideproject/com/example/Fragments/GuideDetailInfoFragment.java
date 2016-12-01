package com.example.tian.tourguideproject.com.example.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.GuideReserveInfoActivity;

/**
 * Created by tian on 2016/11/28.
 */

public class GuideDetailInfoFragment extends Fragment implements View.OnClickListener{

    private TextView nameTxt;
    private TextView ageTxt;
    private TextView sexTxt;
    private TextView levelTxt;
    private TextView certificateIDTxt;
    private TextView workAgeTxt;
    private TextView introTxt;
    private TextView languageTxt;
    private TextView priceTxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.guide_detail_info_fragment, null);
        initView(view);
//
//        Bundle bundle = getArguments();
//        nameTxt.setText(bundle.getString("guideNumID"));
//
//        Log.e("fragment", bundle.getString("guideNumID"));
        return view;
    }

    public void initView(View view){

        nameTxt = (TextView)view.findViewById(R.id.guide_name_txt);
        ageTxt = (TextView) view.findViewById(R.id.guide_age_txt);
        sexTxt = (TextView) view.findViewById(R.id.guide_sex_txt);
        levelTxt = (TextView) view.findViewById(R.id.guide_level_txt);
        certificateIDTxt = (TextView) view.findViewById(R.id.guide_certificateID_txt);
        workAgeTxt = (TextView) view.findViewById(R.id.guide_work_age_txt);
        introTxt = (TextView) view.findViewById(R.id.guide_intro_txt);
        languageTxt = (TextView) view.findViewById(R.id.guide_language_txt);
        priceTxt = (TextView) view.findViewById(R.id.guide_price_txt);

        Button reserve = (Button)view.findViewById(R.id.guide_reserve_btn);
        reserve.setOnClickListener(this);
    }

//    public void getInfoFromActivity(){
//
//        Bundle bundle = getArguments();
//        nameTxt.setText(bundle.getString("guideName"));
//        workAgeTxt.setText(bundle.getString("guideWorkAge"));
//        introTxt.setText(bundle.getString("guideIntro"));
//        priceTxt.setText(bundle.getString("guidePrice"));
////        ageTxt.setText();
////        sexTxt.setText();
////        levelTxt.setText();
////        certificateIDTxt.setText();
////        languageTxt.setText();
//
//    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.guide_reserve_btn:

                Intent intent = new Intent(getActivity(), GuideReserveInfoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
