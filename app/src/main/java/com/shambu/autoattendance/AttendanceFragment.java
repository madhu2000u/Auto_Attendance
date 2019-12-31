package com.shambu.autoattendance;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment {

    @BindView(R.id.set_location_button)
    Button set_location_butt;

    @BindView(R.id.set_location_card)
    CardView cardView;


    public AttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        ButterKnife.bind(this, view);

        SharedPreferences pref = getContext().getSharedPreferences("AutoAtt", 0); // 0 - for private mode

        if(pref.getString("ClassRoomLat", null)==null || pref.getString("ClassRoomLng", null)==null){
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

}
