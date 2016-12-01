package com.example.tian.tourguideproject.com.example.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.GuideReserveInfoActivity;

/**
 * Created by tian on 2016/11/28.
 */

public class GuideDetailInfoFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.guide_detail_info_fragment, null);

        initView(view);

        return view;
    }

    public void initView(View view){

        Button reserve = (Button)view.findViewById(R.id.guide_reserve_btn);
        reserve.setOnClickListener(this);
    }

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
