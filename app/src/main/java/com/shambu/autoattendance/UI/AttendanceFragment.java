package com.shambu.autoattendance.UI;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shambu.autoattendance.AttendanceHistoryTimelineActivity;
import com.shambu.autoattendance.AttendanceListener;
import com.shambu.autoattendance.AttendanceRVadapter;
import com.shambu.autoattendance.AutoAttendanceData;
import com.shambu.autoattendance.DataClasses.AttendanceHistoryPojo;
import com.shambu.autoattendance.DataClasses.SubjectPojo;
import com.shambu.autoattendance.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AttendanceFragment extends Fragment implements AttendanceListener {

    private static final String TAG = "AttendanceFragment";

    private List<SubjectPojo> allSubs;
    private AttendanceRVadapter adapter;
    private AutoAttendanceData data;


    @BindView(R.id.set_location_button)
    Button set_location_butt;

    @BindView(R.id.set_location_card)
    CardView cardView;

    @BindView(R.id.att_rv)
    RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        ButterKnife.bind(this, view);

        allSubs = new ArrayList<>();
        data = new AutoAttendanceData(getContext());
        allSubs = data.getAllDatafromSQL();
        adapter = new AttendanceRVadapter(this, getContext(), allSubs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);


        SharedPreferences pref = getContext().getSharedPreferences("AutoAtt", 0); // 0 - for private mode

        if (pref.getString("ClassRoomLat", null) == null || pref.getString("ClassRoomLng", null) == null) {
            cardView.setVisibility(View.VISIBLE);
        }

        set_location_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SelectClassLocation.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void markAttendanceClick(View view, int position) {

        Log.d(TAG, "The position clicked is "+position);
        switch (view.getId()) {
            case R.id.att_mark_present_imgv :
                Log.d(TAG, "Present for "+allSubs.get(position).getSubjectName());
                AttendanceHistoryPojo pojo1 = new AttendanceHistoryPojo(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()),
                        true, true);
                if(allSubs.get(position).getAttendanceHistory()==null){
                    List<AttendanceHistoryPojo> attendanceHistoryList = new ArrayList<>();
                    attendanceHistoryList.add(pojo1);
                    allSubs.get(position).setAttendanceHistory(attendanceHistoryList);
                    data.updateSubjectAttendance(allSubs.get(position).getSubjectCode(), allSubs.get(position).getAttendanceHistory());
                    adapter.notifyItemChanged(position);
                } else {
                    allSubs.get(position).getAttendanceHistory().add(pojo1);
                    data.updateSubjectAttendance(allSubs.get(position).getSubjectCode(), allSubs.get(position).getAttendanceHistory());
                    adapter.notifyItemChanged(position);
                }
                break;
            case R.id.att_mark_noclass_imgv :
                Log.d(TAG, "Class cancelled for "+allSubs.get(position).getSubjectName());
                AttendanceHistoryPojo pojo2 = new AttendanceHistoryPojo(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()),
                        false, false);
                if(allSubs.get(position).getAttendanceHistory()==null){
                    List<AttendanceHistoryPojo> attendanceHistoryList = new ArrayList<>();
                    attendanceHistoryList.add(pojo2);
                    allSubs.get(position).setAttendanceHistory(attendanceHistoryList);
                    data.updateSubjectAttendance(allSubs.get(position).getSubjectCode(), allSubs.get(position).getAttendanceHistory());
                    adapter.notifyItemChanged(position);
                } else {
                    allSubs.get(position).getAttendanceHistory().add(pojo2);
                    data.updateSubjectAttendance(allSubs.get(position).getSubjectCode(), allSubs.get(position).getAttendanceHistory());
                    adapter.notifyItemChanged(position);
                }
                break;
            case R.id.att_mark_absent_imgv :
                Log.d(TAG, "Absent for "+allSubs.get(position).getSubjectName());
                AttendanceHistoryPojo pojo3 = new AttendanceHistoryPojo(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()),
                        false, true);
                if(allSubs.get(position).getAttendanceHistory()==null){
                    List<AttendanceHistoryPojo> attendanceHistoryList = new ArrayList<>();
                    attendanceHistoryList.add(pojo3);
                    allSubs.get(position).setAttendanceHistory(attendanceHistoryList);
                    data.updateSubjectAttendance(allSubs.get(position).getSubjectCode(), allSubs.get(position).getAttendanceHistory());
                    adapter.notifyItemChanged(position);
                } else {
                    allSubs.get(position).getAttendanceHistory().add(pojo3);
                    data.updateSubjectAttendance(allSubs.get(position).getSubjectCode(), allSubs.get(position).getAttendanceHistory());
                    adapter.notifyItemChanged(position);
                }
                break;
        }
    }

    @Override
    public void openAttendanceHistoryOnClick(int position) {

        Intent intent = new Intent(getContext(), AttendanceHistoryTimelineActivity.class);
        intent.putExtra("SubjectCode", allSubs.get(position).getSubjectCode());
        startActivity(intent);

    }
}
