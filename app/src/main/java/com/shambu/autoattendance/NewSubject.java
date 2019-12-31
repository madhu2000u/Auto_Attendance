package com.shambu.autoattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewSubject extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.subcode_edt)
    TextInputEditText subCode;
    @BindView(R.id.profName_edt)
    TextInputEditText profName;
    @BindView(R.id.minper_edt)
    TextInputEditText subMinPercentage;

    @BindView(R.id.add_button)
    Button addButton;

    @BindView(R.id.mon_chk)
    CheckBox mon;
    @BindView(R.id.tue_chk)
    CheckBox tue;
    @BindView(R.id.wed_chk)
    CheckBox wed;
    @BindView(R.id.thur_chk)
    CheckBox thur;
    @BindView(R.id.fri_chk)
    CheckBox fri;
    @BindView(R.id.sat_chk)
    CheckBox sat;
    @BindView(R.id.sun_chk)
    CheckBox sun;

    @BindView(R.id.mon_from)
    TextView fmon;
    @BindView(R.id.tue_from)
    TextView ftue;
    @BindView(R.id.wed_from)
    TextView fwed;
    @BindView(R.id.thur_from)
    TextView fthur;
    @BindView(R.id.fri_from)
    TextView ffri;
    @BindView(R.id.sat_from)
    TextView fsat;
    @BindView(R.id.sun_from)
    TextView fsun;
    @BindView(R.id.mon_to)
    TextView tmon;
    @BindView(R.id.tue_to)
    TextView ttue;
    @BindView(R.id.wed_to)
    TextView twed;
    @BindView(R.id.thur_to)
    TextView tthur;
    @BindView(R.id.fri_to)
    TextView tfri;
    @BindView(R.id.sat_to)
    TextView tsat;
    @BindView(R.id.sun_to)
    TextView tsun;

    private int mHour, mMin;
    private List<SubjectSchedulePojo> subSchedule;
    private SubjectPojo subjectData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_subject);
        ButterKnife.bind(this);

        fmon.setOnClickListener(this);
        tmon.setOnClickListener(this);
        ftue.setOnClickListener(this);
        ttue.setOnClickListener(this);
        fwed.setOnClickListener(this);
        twed.setOnClickListener(this);
        fthur.setOnClickListener(this);
        tthur.setOnClickListener(this);
        ffri.setOnClickListener(this);
        tfri.setOnClickListener(this);
        fsat.setOnClickListener(this);
        tsat.setOnClickListener(this);
        fsun.setOnClickListener(this);
        tsun.setOnClickListener(this);

        subSchedule = new ArrayList<>();

    }

    @Override
    public void onClick(View view) {
        TextView v = (TextView) view;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                if (min == 0) {
                    v.setText(hour + ":" + min + "0");
                } else {
                    v.setText(hour + ":" + min);
                }
            }
        }, mHour, mMin, true);
        timePickerDialog.show();
    }

    @OnClick(R.id.add_button)
    void saveSubject() {
        if (mon.isChecked()) {
            SubjectSchedulePojo ss = new SubjectSchedulePojo("Mon",
                    textViewToTime(fmon), textViewToTime(tmon));
            subSchedule.add(ss);
        }
        if (tue.isChecked()) {
            SubjectSchedulePojo ss = new SubjectSchedulePojo("Tue",
                    textViewToTime(ftue), textViewToTime(ttue));
            subSchedule.add(ss);
        }
        if (wed.isChecked()) {
            SubjectSchedulePojo ss = new SubjectSchedulePojo("Wed",
                    textViewToTime(fwed), textViewToTime(twed));
            subSchedule.add(ss);
        }
        if (thur.isChecked()) {
            SubjectSchedulePojo ss = new SubjectSchedulePojo("Thur",
                    textViewToTime(fthur), textViewToTime(tthur));
            subSchedule.add(ss);
        }
        if (fri.isChecked()) {
            SubjectSchedulePojo ss = new SubjectSchedulePojo("Fri",
                    textViewToTime(ffri), textViewToTime(tfri));
            subSchedule.add(ss);
        }
        if (sat.isChecked()) {
            SubjectSchedulePojo ss = new SubjectSchedulePojo("Sat",
                    textViewToTime(fsat), textViewToTime(tsat));
            subSchedule.add(ss);
        }
        if (sun.isChecked()) {
            SubjectSchedulePojo ss = new SubjectSchedulePojo("Sun",
                    textViewToTime(fsun), textViewToTime(tsun));
            subSchedule.add(ss);
        }
        subjectData = new SubjectPojo(subCode.getText().toString(), profName.getText().toString(),
                Integer.parseInt(subMinPercentage.getText().toString()), subSchedule);
        finish();
    }

    private Time textViewToTime(TextView tv) {
        int h, m;
        String tvText = tv.getText().toString();
        h = Integer.parseInt(String.valueOf(tvText.charAt(1))) * 1 + Integer.parseInt(String.valueOf(tvText.charAt(0))) * 10;
        m = Integer.parseInt(String.valueOf(tvText.charAt(4))) * 1 + Integer.parseInt(String.valueOf(tvText.charAt(3))) * 10;

        Time time = new Time(h, m, 0);
        return time;
    }
}
