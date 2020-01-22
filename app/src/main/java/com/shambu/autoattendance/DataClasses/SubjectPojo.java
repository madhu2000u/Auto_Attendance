package com.shambu.autoattendance.DataClasses;

import java.util.List;

public class SubjectPojo {
    private int minPer, sqlID;
    private String subjectCode, subjectName, subjectProF, subColor = "#FF00FF";
    private List<SubjectSchedulePojo> schedule;
    private List<AttendanceHistoryPojo> attendanceHistory;

    public SubjectPojo(int sqlID, int minPer,
                       String subjectCode, String subjectName, String subjectProF,
                       List<SubjectSchedulePojo> schedule, List<AttendanceHistoryPojo> attendanceHistory) {
        this.sqlID = sqlID;
        this.minPer = minPer;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectProF = subjectProF;
        this.schedule = schedule;
        this.attendanceHistory = attendanceHistory;
    }

    public SubjectPojo(int sqlID, int minPer,
                        String subjectCode, String subjectName, String subjectProF,
                        List<SubjectSchedulePojo> schedule) {
        this.sqlID = sqlID;
        this.minPer = minPer;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectProF = subjectProF;
        this.schedule = schedule;
    }

    public SubjectPojo(int minPer,
                       String subjectCode, String subjectName, String subjectProF,
                       List<SubjectSchedulePojo> schedule, List<AttendanceHistoryPojo> attendanceHistory) {
        this.minPer = minPer;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectProF = subjectProF;
        this.schedule = schedule;
        this.attendanceHistory = attendanceHistory;
    }

    public SubjectPojo(int minPer,
                       String subjectCode, String subjectName, String subjectProF,
                       List<SubjectSchedulePojo> schedule) {
        this.minPer = minPer;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectProF = subjectProF;
        this.schedule = schedule;
    }

    public int getSqlID() {
        return sqlID;
    }

    public int getMinPer() {
        return minPer;
    }

    public void setMinPer(int minPer) {
        this.minPer = minPer;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectProF() {
        return subjectProF;
    }

    public void setSubjectProF(String subjectProF) {
        this.subjectProF = subjectProF;
    }

    public String getSubColor() {
        return subColor;
    }

    public void setSubColor(String subColor) {
        this.subColor = subColor;
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
