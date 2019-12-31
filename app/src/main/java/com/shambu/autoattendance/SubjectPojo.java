package com.shambu.autoattendance;

import java.util.List;

public class SubjectPojo {
    private String subCode, subProf;
    private int minPercentage;
    private List<SubjectSchedulePojo> schedule;
    private List<AttendanceHistoryPojo> attendanceHistory;

    public SubjectPojo(String subCode, String subProf, int minPercentage,
                       List<SubjectSchedulePojo> schedule, List<AttendanceHistoryPojo> attendanceHistory) {
        this.subCode = subCode;
        this.subProf = subProf;
        this.minPercentage = minPercentage;
        this.schedule = schedule;
        this.attendanceHistory = attendanceHistory;
    }

    public SubjectPojo(String subCode, String subProf, int minPercentage,
                       List<SubjectSchedulePojo> schedule) {
        this.subCode = subCode;
        this.subProf = subProf;
        this.minPercentage = minPercentage;
        this.schedule = schedule;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubProf() {
        return subProf;
    }

    public void setSubProf(String subProf) {
        this.subProf = subProf;
    }

    public int getMinPercentage() {
        return minPercentage;
    }

    public void setMinPercentage(int minPercentage) {
        this.minPercentage = minPercentage;
    }

    public List<SubjectSchedulePojo> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<SubjectSchedulePojo> schedule) {
        this.schedule = schedule;
    }

    public List<AttendanceHistoryPojo> getAttendanceHistory() {
        return attendanceHistory;
    }

    public void setAttendanceHistory(List<AttendanceHistoryPojo> attendanceHistory) {
        this.attendanceHistory = attendanceHistory;
    }
}
