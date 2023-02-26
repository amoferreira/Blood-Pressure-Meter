package com.example.app2_blood_pressure_v2;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {

    TextView input_time, input_sp, input_dp, input_hr, input_level;
    private Integer user_id;
    private DatabaseHelper databaseHelper;
    List<PressureRead> reads_user;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user_id = getArguments().getInt("user_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        input_time = view.findViewById(R.id.input_time);
        input_dp = view.findViewById(R.id.input_dp);
        input_sp = view.findViewById(R.id.input_sp);
        input_hr = view.findViewById(R.id.input_hr);
        input_level = view.findViewById(R.id.input_level);

        databaseHelper = new DatabaseHelper(getActivity());
        reads_user = databaseHelper.getAllReadsByUser(user_id);

        //LER
        String reads_text_time="\n";
        String reads_text_dp="\n";
        String reads_text_sp="\n";
        String reads_text_hr="\n";
        SpannableStringBuilder reads_level = new SpannableStringBuilder();

        Integer reads_n=reads_user.size();
        int i=0;
        while(i<reads_n){
            PressureRead pressure_read_i = reads_user.get(i);
            // Get time inputs
            String time_read_i = pressure_read_i.getTimeRead().substring(0,19); // string without miliseconds
            reads_text_time = time_read_i.substring(0,10) + "\n" + time_read_i.substring(10,19) + "\n \n" + reads_text_time;
            // Get pressure and heart rate inputs
            reads_text_dp = pressure_read_i.getDiastolicPressure() + "\n \n \n" + reads_text_dp;
            reads_text_sp = pressure_read_i.getSystolicPressure() + "\n \n \n" + reads_text_sp;
            reads_text_hr = pressure_read_i.getHeartRate() + "\n \n \n" + reads_text_hr;
            // Get level and its color
            int level_color_i = getColor(pressure_read_i.getSystolicPressure(), pressure_read_i.getDiastolicPressure());
            SpannableStringBuilder level_read_i = getLevel(pressure_read_i.getSystolicPressure(), pressure_read_i.getDiastolicPressure());
            level_read_i.setSpan(new ForegroundColorSpan(level_color_i), 0, level_read_i.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            reads_level = level_read_i.append("\n \n \n").append(reads_level);

            i=i+1;
        }
        input_time.setText(reads_text_time);
        input_dp.setText(reads_text_dp);
        input_sp.setText(reads_text_sp);
        input_hr.setText(reads_text_hr);
        input_level.setText(reads_level);

        return view;
    }

    public @ColorInt int getColor(int sp, int dp){
        if (sp<=90 && dp<=60){
           return 0xFF00BCD4;
        } else if ((sp>90 || dp>60) && (sp<=120 && dp<=80)){
            return 0xFF4CAF50;
        } else if ((sp>120 || dp>80) && (sp<=140 && dp<=90)) {
            return 0xFFE6DB00;
        } else if ((sp>140 || dp>90) && (sp<=160 && dp<=100)) {
            return 0xFFFF9800;
        } else if (sp>160 || dp>100) {
            return 0xFFFF5722;
        }
        return 0xFFFF5722;
    }
    public SpannableStringBuilder getLevel(int sp, int dp){
        if (sp<=90 && dp<=60){
            return new SpannableStringBuilder("Low");
        } else if ((sp>90 || dp>60) && (sp<=120 && dp<=80)){
            return new SpannableStringBuilder("Normal");
        } else if ((sp>120 || dp>80) && (sp<=140 && dp<=90)) {
            return new SpannableStringBuilder("Elevated");
        } else if ((sp>140 || dp>90) && (sp<=160 && dp<=100)) {
            return new SpannableStringBuilder("High 1");
        } else if (sp>160 || dp>100) {
            return new SpannableStringBuilder("High 2");
        }
        return new SpannableStringBuilder("\n");
    }

}