package com.example.app2_blood_pressure_v2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StatisticsFragment extends Fragment {

    LineGraphSeries<DataPoint> series_sp;
    LineGraphSeries<DataPoint> series_dp;
    LineGraphSeries<DataPoint> series_hr;
    private GraphView graph_1;
    private GraphView graph_2;
    private DatabaseHelper databaseHelper;
    private Integer user_id;
    List<PressureRead> reads_user;
    int days, month, year;
    int reads_n;
    String dateBeforeStr;

    public StatisticsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        graph_1 = (GraphView) view.findViewById(R.id.graph_1);
        graph_2 = (GraphView) view.findViewById(R.id.graph_2);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.number_of_days, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        series_sp = new LineGraphSeries<>();
        series_dp = new LineGraphSeries<>();
        series_hr = new LineGraphSeries<>();

        databaseHelper = new DatabaseHelper(getActivity());

        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime(); // in this format "yyyy-MM-dd HH:mm:ss" to consider the reads done in the current day
        String currentDateStr = dateFormat.format(currentDate);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(position== 0){ //All
                    //number of reads of all reads
                    reads_user = databaseHelper.getAllReadsByUser(user_id);
                    //updateGraph
                    System.out.println("Reads all days:  " + reads_user.size());
                    cleanGraphData();
                    createGraphs(reads_user.size());
                    addGraphData(reads_user.size());
                }
                else{
                    System.out.println("Current date: " + currentDateStr);
                    if(position==1){ //Today
                        days = 0;
                        dateBeforeStr = getCalculatedLastDays(df,cal.getTime(), days);
                        System.out.println(days + " days before: " + dateBeforeStr );
                    }
                    else if(position == 2){ //Last 3 days
                        days = -3;
                        dateBeforeStr = getCalculatedLastDays(df,cal.getTime(), days);
                        System.out.println(days + " days before: " + dateBeforeStr );
                    }
                    else if(position == 3){ //Last 5 days
                        days = -5;
                        dateBeforeStr = getCalculatedLastDays(df,cal.getTime(), days);
                        System.out.println(days + " days before: " + dateBeforeStr );
                    }
                    else if(position == 4){ //week
                        days = -7;
                        dateBeforeStr = getCalculatedLastDays(df,cal.getTime(), days);
                        System.out.println(days + " days before: " + dateBeforeStr );
                    }
                    else if(position == 5){ //Last month
                        month = -1;
                        dateBeforeStr = getCalculatedLastMonth(df,cal.getTime(), month);
                        System.out.println(month + " month before: " + dateBeforeStr );
                    }
                    else if(position == 6){
                        year = -1;
                        dateBeforeStr = getCalculatedLastYear(df,cal.getTime(), year);
                        System.out.println(year + " year before: " + dateBeforeStr );
                    }

                    reads_user = databaseHelper.getNdaysReadsofUser(user_id, dateBeforeStr, currentDateStr);

                    cleanGraphData();
                    addGraphData(reads_user.size());
                    createGraphs(reads_user.size());
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        return view;
    }

    // Functions to get last days, month or year
    public static String getCalculatedLastDays(SimpleDateFormat dateFormat, Date currentDate, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_YEAR, days);
        Date newDate = cal.getTime();
        return dateFormat.format(newDate);
    }

    public static String getCalculatedLastMonth(SimpleDateFormat dateFormat, Date currentDate, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MONTH, month);
        Date newDate = cal.getTime();
        return dateFormat.format(newDate);
    }

    public static String getCalculatedLastYear(SimpleDateFormat dateFormat, Date currentDate, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.YEAR, year);
        Date newDate = cal.getTime();
        return dateFormat.format(newDate);
    }

    //function to set content on graph
    public void createGraphs(int maxX){
            series_sp.setColor(0xFFF44336);
            series_dp.setColor(0xFF8BC34A);
            series_sp.setTitle("Systolic pressure");
            series_dp.setTitle("Diastolic pressure");

            graph_2.getViewport().setMinY(30);
            graph_2.getViewport().setMaxY(140);
            graph_2.getViewport().setYAxisBoundsManual(true);

            graph_1.getViewport().setMinY(40);
            graph_1.getViewport().setMaxY(200);
            graph_1.getViewport().setYAxisBoundsManual(true);

            graph_1.getViewport().setXAxisBoundsManual(true);
            graph_1.getViewport().setMinX(0);
            graph_2.getViewport().setXAxisBoundsManual(true);
            graph_2.getViewport().setMinX(0);

            if(maxX== 0){
                maxX = 1;
            }
            if(maxX == 1){
                graph_1.getViewport().setMaxX(maxX);
                graph_2.getViewport().setMaxX(maxX);
            }
            else {
                graph_1.getViewport().setMaxX(maxX - 1);
                graph_2.getViewport().setMaxX(maxX - 1);
            }
            graph_1.getGridLabelRenderer().setHorizontalLabelsVisible(false);
            graph_2.getGridLabelRenderer().setHorizontalLabelsVisible(false);


    }
    //function to clean data
    public void cleanGraphData(){
        series_sp.resetData(new DataPoint[] {});
        series_dp.resetData(new DataPoint[] {});
        series_hr.resetData(new DataPoint[] {});
    }
    //function to add data in graph
    public void addGraphData(int reads_n){
        int i=0;
        while(i<reads_n){
            PressureRead pressure_read_i = reads_user.get(i);
            String reads_text_time = pressure_read_i.getTimeRead();
            int reads_text_dp = pressure_read_i.getDiastolicPressure();
            int reads_text_sp = pressure_read_i.getSystolicPressure();
            int reads_text_hr = pressure_read_i.getHeartRate();
            series_sp.appendData (new DataPoint(i,reads_text_sp),true,100);
            series_dp.appendData (new DataPoint(i,reads_text_dp),true,100);
            series_hr.appendData (new DataPoint(i,reads_text_hr),true,100);

            i=i+1;
        }

        graph_1.addSeries(series_sp);
        graph_1.addSeries(series_dp);
        graph_2.addSeries(series_hr);
        series_sp.setDrawDataPoints(true);
        series_dp.setDrawDataPoints(true);
        series_hr.setDrawDataPoints(true);
    }
}