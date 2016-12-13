package com.example.tian.tourguideproject.com.example.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tian.tourguideproject.R;
import com.example.tian.tourguideproject.com.example.bean.SimpleGuideInfoListItem;
import java.util.List;

/**
 * Created by tian on 2016/11/25.
 * 预约--查看导游--导游的简要信息
 *
 * 使用接口回调的方式处理listview内部的点击事件
 */

public class GuideInfoListAdapter extends BaseAdapter implements View.OnClickListener{

    private SimpleGuideInfoListItem guideInfo;
    private LayoutInflater inflater;
    private List<SimpleGuideInfoListItem> item;

    private ViewHolder viewHolder;

    /**
     * 所有listview的item共用同一个 mCallback
     */
    private Callback mCallback;

    /**
     * 自定义接口，用于回调按钮点击事件到Activity
     */
    public interface Callback{
        public void clickInListView(View view);
    }

    /**
     * 响应按钮点击事件,调用子定义接口，并传入View
     * @param v
     */
    @Override
    public void onClick(View v) {
        mCallback.clickInListView(v);
    }

    public GuideInfoListAdapter(Context context, int id, List<SimpleGuideInfoListItem> data,
                                Callback callback) {
        this.inflater = LayoutInflater.from(context);
        this.item = data;
        mCallback = callback;  /***/
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public SimpleGuideInfoListItem getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        guideInfo = getItem(position);

        if(convertView == null){

            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.guide_reserve_guide_list_item, null);

            viewHolder.guideImageItem = (ImageView)convertView.findViewById(R.id.guide_image_list_item);
            viewHolder.guideNameItem = (TextView)convertView.findViewById(R.id.guide_name_list_item);
            viewHolder.guideWorkAgeItem = (TextView)convertView.findViewById(R.id.guide_work_age_list_item);
            viewHolder.guideIntroItem = (TextView)convertView.findViewById(R.id.guide_intro_list_item);
            viewHolder.guidePriceItem = (TextView)convertView.findViewById(R.id.guide_price_list_item);
            viewHolder.guideNumID = (TextView)convertView.findViewById(R.id.guide_NumID_list_item);
            viewHolder.check = (TextView)convertView.findViewById(R.id.check_btn_guide_list_item);
            /**
             * 把组件引用通过setTag方法附加在View上面，
             * 当加载第二页的时候，你就不用再次去findViewById了，
             * 直接用getTag方法来取出数据引用即可。
             */
            convertView.setTag(viewHolder);
        }else{
            //直接用getTag方法来取出数据引用
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.guideImageItem.setBackground(guideInfo.getGuideImage());
        viewHolder.guideNameItem.setText(guideInfo.getGuideName());
        viewHolder.guideWorkAgeItem.setText(guideInfo.getGuideWorkAge());
        viewHolder.guideIntroItem.setText(guideInfo.getGuideIntro());
        viewHolder.guidePriceItem.setText(guideInfo.getGuidePrice());
        viewHolder.guideNumID.setText(guideInfo.getGuideNumID());

        viewHolder.guideImageItem.setOnClickListener(this);
        viewHolder.guideNameItem.setOnClickListener(this);
        viewHolder.check.setOnClickListener(this);

        /**
         *设置当前按钮所在的位置
         */
        viewHolder.guideImageItem.setTag(position);
        viewHolder.guideNameItem.setTag(position);
        viewHolder.check.setTag(position);

        return convertView;
    }

    private class ViewHolder{

        ImageView guideImageItem;
        TextView guideNameItem;
        TextView guideWorkAgeItem;
        TextView guideIntroItem;
        TextView guidePriceItem;
        TextView guideNumID;
        TextView check;
    }

}
