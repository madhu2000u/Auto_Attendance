package com.shambu.autoattendance;

import android.view.View;

public interface AttendanceListener {

    void markAttendanceClick(View view, int position);
    void openAttendanceHistoryOnClick(int position);
}
