package com.shambu.autoattendance;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TimeTableFragment extends Fragment {

    @BindView(R.id.add_subject_timings)
    FloatingActionButton add_fab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_table, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick(R.id.add_subject_timings)
    void addNewSubject(){
        startActivity(new Intent(getContext(), NewSubject.class));
    }
}
