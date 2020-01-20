package com.shambu.autoattendance;

import java.sql.Date;

public class AttendanceHistoryPojo {
    private Date date;
    private Boolean attendance;
    private Boolean classHappened;

    public AttendanceHistoryPojo(Date date, Boolean attendance, Boolean classHappened) {
        this.date = date;
        this.attendance = attendance;
        this.classHappened = classHappened;
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

    public Boolean getClassHappened() {
        return classHappened;
    }

    public void setClassHappened(Boolean classHappened) {
        this.classHappened = classHappened;
    }
}
