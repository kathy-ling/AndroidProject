package com.example.tian.tourguideproject.com.example.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tian.tourguideproject.R;

import java.util.Calendar;

/**
 * Created by tian on 2016/11/30.
 */

public class ReleaseOrderFragment extends Fragment implements
        View.OnClickListener{

    private LinearLayout chooseTimeLayout;

    private EditText timeTxt;
    private EditText personNumTxt;
    private EditText telTxt;
    private EditText budgetTxt;
    private EditText otherCommand;

    private Button releaseOrder;

    final Calendar calendar = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.release_order_fragment, null);

        initView(view);

        return view;
    }

    public void initView(View view){

        chooseTimeLayout = (LinearLayout)view.findViewById(R.id.choose_time_layout);

        timeTxt = (EditText)view.findViewById(R.id.time_txt);
        personNumTxt = (EditText)view.findViewById(R.id.person_num_txt);
        telTxt = (EditText)view.findViewById(R.id.tel_txt);
        budgetTxt = (EditText)view.findViewById(R.id.budget_txt);
        otherCommand = (EditText)view.findViewById(R.id.other_command);

        releaseOrder = (Button)view.findViewById(R.id.release_order_btn);

        timeTxt.setOnClickListener(this);
        chooseTimeLayout.setOnClickListener(this);
        releaseOrder.setOnClickListener(this);
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

                DatePickerDialog dialog = new DatePickerDialog(getContext(),listener,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();

                break;
            case R.id.release_order_btn:

                Toast.makeText(getContext(), "release order", Toast.LENGTH_SHORT).show();
                break;
        }
    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 返回上一个fragment
            Toast.makeText(getContext(), "back click", Toast.LENGTH_SHORT).show();
            getFragmentManager().popBackStack();
            return true;
        }
        return false;
    }
}
