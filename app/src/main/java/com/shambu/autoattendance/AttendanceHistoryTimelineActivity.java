package com.shambu.autoattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class AttendanceHistoryTimelineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history_timeline);

        Toast.makeText(getApplicationContext(), getIntent().getStringExtra("SubjectCode"), Toast.LENGTH_SHORT).show();
    }
}

