package com.example.tian.tourguideproject.com.example;


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

    LinearLayout ll;
    //初始化控件
    ImageView iv ;
    TextView tv1;
    TextView tv2;


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
        iv = (ImageView) view.findViewById (R.id.iv_img);
        tv1 = (TextView) view.findViewById (R.id.tv_name);
        tv2 = (TextView) view.findViewById (R.id.tv_description);
        ll = (LinearLayout) view.findViewById(R.id.ll_marker);
        try
        {
            iv.setBackgroundResource(info.getImgId());
            tv1.setText(info.getName());
            tv2.setText(info.getDescription());
            ll.setVisibility(View.VISIBLE);
        }
        catch (Exception e)
        {

        }

        return view;
    }

}
