package com.example.tian.tourguideproject.com.example.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.bean.OverLayInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClickOverlayFragment extends Fragment {

    //初始化控件
    LinearLayout ll_showMarkInfo;
    ImageView iv_overlayImg ;
    TextView tv_overlayName;
    TextView tv_overlayDecription;


    public ClickOverlayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_click_overlay, null);
        //参数接收
        Bundle bundle = getArguments();
        OverLayInfo info =(OverLayInfo) bundle.getSerializable("Info");
        iv_overlayImg = (ImageView) view.findViewById (R.id.iv_overlayImg);
        tv_overlayName = (TextView) view.findViewById (R.id.tv_overlayName);
        tv_overlayDecription = (TextView) view.findViewById (R.id.tv_overlayDescription);
        ll_showMarkInfo = (LinearLayout) view.findViewById(R.id.ll_marker);
        //显示详细信息
        try
        {
            iv_overlayImg.setBackgroundResource(info.getImgId());
            tv_overlayName.setText(info.getName());
            tv_overlayDecription.setText(info.getDescription());
            ll_showMarkInfo.setVisibility(View.VISIBLE);
        }
        catch (Exception e)
        {

        }

        return view;
    }

}
