package com.shambu.autoattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

public class AutoAttendanceData extends SQLiteOpenHelper {

    private static final String TAG = "AutoAttendanceData";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "attendance";
    private static final String TABLE_NAME = "AppData";
    public static final String COL_ID = "Sno";
    private static final String COL_SUBCODE = "Subject";
    public static final String COL_SUBPROF = "Faculty";
    public static final String COL_MINPER = "MinimumPercentage";
    public static final String COL_SCHEDULE = "Schedule";
    public static final String COL_ATTENDANCE = "Attendance";

    public AutoAttendanceData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_SUBCODE + " TEXT NOT NULL, " + COL_SUBPROF + " TEXT NOT NULL, " + COL_MINPER + " INTEGER NOT NULL, " +
                COL_SCHEDULE + " TEXT NOT NULL, " + COL_ATTENDANCE + " TEXT NOT NULL );");

        ContentValues values = new ContentValues();
        values.put(COL_SUBCODE, "");
        values.put(COL_SUBPROF, "");
        values.put(COL_MINPER, 0);
        values.put(COL_SCHEDULE, "");
        values.put(COL_SUBCODE, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addNewSubtoSQL(SubjectPojo subjectData){

        String sche =  new Gson().toJson(subjectData.getSchedule());
        String ah =  new Gson().toJson(subjectData.getAttendanceHistory());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_SUBCODE, subjectData.getSubCode());
        values.put(COL_SUBPROF, subjectData.getSubProf());
        values.put(COL_MINPER, subjectData.getMinPercentage());
        values.put(COL_SCHEDULE, sche);  Log.d(TAG, "Schedule "+sche);
        values.put(COL_SCHEDULE, ah);  Log.d(TAG, "AH "+ah);

        db.insert(TABLE_NAME, null, values);
    }

    public void updateTimings(String code, SubjectSchedulePojo schedulePojo){

    }
}
