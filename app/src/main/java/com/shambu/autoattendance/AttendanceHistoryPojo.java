package com.shambu.autoattendance;

import java.sql.Date;

public class AttendanceHistoryPojo {
    private Date date;
    private Boolean attendance;

    public AttendanceHistoryPojo(Date date, Boolean attendance) {
        this.date = date;
        this.attendance = attendance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }
}
