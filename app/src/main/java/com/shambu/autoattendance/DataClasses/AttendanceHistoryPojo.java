package com.shambu.autoattendance.DataClasses;

public class AttendanceHistoryPojo {
    private String date;
    private Boolean attendance;
    private Boolean classHappened;

    public AttendanceHistoryPojo(String date, Boolean attendance, Boolean classHappened) {
        this.date = date;
        this.attendance = attendance;
        this.classHappened = classHappened;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
