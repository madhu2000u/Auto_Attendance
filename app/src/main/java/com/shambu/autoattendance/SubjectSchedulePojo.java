package com.shambu.autoattendance;

public class SubjectSchedulePojo {
    private int day, fH, fM, tH, tM;

    public SubjectSchedulePojo(int day, int fH, int fM, int tH, int tM) {
        this.day = day;
        this.fH = fH;
        this.fM = fM;
        this.tH = tH;
        this.tM = tM;

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getfH() {
        return fH;
    }

    public void setfH(int fH) {
        this.fH = fH;
    }

    public int getfM() {
        return fM;
    }

    public void setfM(int fM) {
        this.fM = fM;
    }

    public int gettH() {
        return tH;
    }

    public void settH(int tH) {
        this.tH = tH;
    }

    public int gettM() {
        return tM;
    }

    public void settM(int tM) {
        this.tM = tM;
    }
}
