package com.shambu.autoattendance;


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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AttendanceFragment extends Fragment implements AttendanceMarkingListener {

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
                break;
            case R.id.att_mark_noclass_imgv :
                Log.d(TAG, "Class cancelled for "+allSubs.get(position).getSubjectName());
                break;
            case R.id.att_mark_absent_imgv :
                Log.d(TAG, "Absent for "+allSubs.get(position).getSubjectName());
                break;
        }
    }
}
