package com.shambu.autoattendance;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.eunsiljo.timetablelib.data.TimeData;
import com.github.eunsiljo.timetablelib.data.TimeTableData;
import com.github.eunsiljo.timetablelib.view.TimeTableView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TimeTableFragment extends Fragment {

    private static final String TAG = "TimeTableFragment";

    private List<SubjectPojo> allSubjectsData;
    private AutoAttendanceData data;

    private List<String> daysOftheWeek = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat","Sun");
    private long mNow = 0;

    @BindView(R.id.add_subject_timings)
    FloatingActionButton add_fab;

    @BindView(R.id.timeTable)
    TimeTableView ttv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_table, container, false);
        ButterKnife.bind(this, view);

        data = new AutoAttendanceData(getContext());
        allSubjectsData = new ArrayList<>();
        allSubjectsData = data.getAllDatafromSQL();


        Log.d(TAG, "Size of data from SQL = "+allSubjectsData.size());
        initTimetable();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initTimetable();
    }

    @Override
    public void onResume() {
        super.onResume();
        initTimetable();
    }

    @OnClick(R.id.add_subject_timings)
    void addNewSubject(){
        startActivity(new Intent(getContext(), NewSubject.class));
    }

    private void initTimetable(){
        DateTime now = DateTime.now();
        mNow = now.withTimeAtStartOfDay().getMillis();
        ttv.setTableMode(TimeTableView.TableMode.SHORT);
        ttv.setShowHeader(true);
        ttv.setStartHour(7);
        ttv.setTimeTable(mNow, timetableSQLdata());
    }

    private ArrayList<TimeTableData> timetableSQLdata(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(calendar.getTime());
        Log.d(TAG, "Todays date is: "+date);
        TypedArray colors_table_light = getResources().obtainTypedArray(R.array.colors_table_light);
        ArrayList<TimeTableData> tables = new ArrayList<>();

        int dayloop = 0;
        while(dayloop < 7){
            ArrayList<TimeData> values = new ArrayList<>();
            for(int i = 0 ; i< allSubjectsData.size(); i++){
                for (int j = 0; j < allSubjectsData.get(i).getSchedule().size(); j++){
                    if(allSubjectsData.get(i).getSchedule().get(j).getDay()==dayloop){
                        values.add(new TimeData(i, allSubjectsData.get(i).getSubjectName(),
                                colors_table_light.getResourceId(i, 0),
                                getMillis( date+" "+timefromto(allSubjectsData.get(i).getSchedule().get(j).getfH(), allSubjectsData.get(i).getSchedule().get(j).getfM())),
                                getMillis(date+" "+timefromto(allSubjectsData.get(i).getSchedule().get(j).gettH(), allSubjectsData.get(i).getSchedule().get(j).gettM()))));
                    }
                }
            }
            sortValues(values);
            tables.add(new TimeTableData(daysOftheWeek.get(dayloop), values));
            dayloop++;
        }
        return tables;
    }

    private void sortValues(ArrayList<TimeData> values){

        int i, j, min_idx;

        // One by one move boundary of unsorted subarray
        for (i = 0; i < values.size()-1; i++)
        {
            // Find the minimum element in unsorted array
            min_idx = i;
            for (j = i+1; j < values.size(); j++)
                if (values.get(j).getStartMills() < values.get(min_idx).getStartMills())
                    min_idx = j;

            // Swap the found minimum element with the first element
            Collections.swap(values, min_idx, i);
        }
    }

    private String timefromto(int hour, int min){
        String v;
        if(hour/10 == 0){
            if(min/10 == 0){
                v="0"+hour+":0"+min+":00";
            }
            else{
                v="0"+hour + ":" + min+":00";
            }
        }
        else {
            if(min/10 == 0){
                v=hour + ":0" + min+":00";
            }
            else{
                v=hour + ":" + min+":00";
            }
        }

        return v;
    }
    private long getMillis(String day){
        DateTime date = getDateTimePattern().parseDateTime(day);
        return date.getMillis();
    }

    private DateTimeFormatter getDateTimePattern(){
        return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    }

}
