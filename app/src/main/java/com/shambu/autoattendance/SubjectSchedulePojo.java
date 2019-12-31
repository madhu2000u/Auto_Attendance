package com.shambu.autoattendance;

import java.sql.Time;

public class SubjectSchedulePojo {
    private String day;
    private Time fromTime, toTime;

    public SubjectSchedulePojo(String day, Time fromTime, Time toTime) {
        this.day = day;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Time getFromTime() {
        return fromTime;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public Time getToTime() {
        return toTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }
}
