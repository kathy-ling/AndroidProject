package com.example.tian.tourguideproject.com.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.adapter.GuideInfoListAdapter;
import com.example.tian.tourguideproject.com.example.bean.SimpleGuideInfoListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tian on 2016/11/25.
 */

public class ReserveGuideFragment extends Fragment{

    private List<SimpleGuideInfoListItem> guideInfoList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.guide_reserve_main, null);

        initGuideInfo();

        GuideInfoListAdapter adapter = new GuideInfoListAdapter(getActivity().getApplication(),
                R.layout.guide_reserve_guide_list, guideInfoList);
        ListView listView = (ListView)view.findViewById(R.id.reserve_guide_listview);
        listView.setAdapter(adapter);

        return view;
    }

    public void initGuideInfo(){

        int[] itemIconRes = {R.drawable.linkeer};

        SimpleGuideInfoListItem guide1 = new SimpleGuideInfoListItem(null,
                "林可儿", "3年", "my selef introduction 我94年取得导游证，一直从事北京地接服务，到现在从业22年，讲解生动，经验丰富，热情大方", "￥350/天");
        SimpleGuideInfoListItem guide2 = new SimpleGuideInfoListItem(null,
                "林可儿", "3年", "my selef introduction 我94年取得导游证，一直从事北京地接服务，到现在从业22年，讲解生动，经验丰富，热情大方", "￥350/天");
        SimpleGuideInfoListItem guide3 = new SimpleGuideInfoListItem(null,
                "林可儿", "3年", "my selef introduction 我94年取得导游证，一直从事北京地接服务，到现在从业22年，讲解生动，经验丰富，热情大方", "￥350/天");
        SimpleGuideInfoListItem guide4 = new SimpleGuideInfoListItem(null,
                "林可儿", "3年", "my selef introduction 我94年取得导游证，一直从事北京地接服务，到现在从业22年，讲解生动，经验丰富，热情大方", "￥350/天");
        SimpleGuideInfoListItem guide5 = new SimpleGuideInfoListItem(null,
                "林可儿", "3年", "my selef introduction 我94年取得导游证，一直从事北京地接服务，到现在从业22年，讲解生动，经验丰富，热情大方", "￥350/天");
        SimpleGuideInfoListItem guide6 = new SimpleGuideInfoListItem(null,
                "林可儿", "3年", "my selef introduction 我94年取得导游证，一直从事北京地接服务，到现在从业22年，讲解生动，经验丰富，热情大方", "￥350/天");

        guideInfoList.add(guide1);
        guideInfoList.add(guide2);
        guideInfoList.add(guide3);
        guideInfoList.add(guide4);
        guideInfoList.add(guide5);
        guideInfoList.add(guide6);
    }
}
