package com.shambu.autoattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AutoAttendanceData extends SQLiteOpenHelper {

    private static final String TAG = "AutoAttendanceData";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "attendance";
    private static final String TABLE_NAME = "AppData";
    public static final String COL_ID = "Sno";
    private static final String COL_SUBCODE = "Subject";
    private static final String COL_SUBNAME = "SubjectName";
    public static final String COL_SUBPROF = "Faculty";
    public static final String COL_MINPER = "MinimumPercentage";
    public static final String COL_SCHEDULE = "Schedule";
    public static final String COL_ATTENDANCE = "Attendance";

    public AutoAttendanceData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_SUBCODE + " TEXT NOT NULL, " +
                COL_SUBNAME + " TEXT NOT NULL, " +
                COL_SUBPROF + " TEXT NOT NULL, " +
                COL_MINPER + " INTEGER NOT NULL, " +
                COL_SCHEDULE + " TEXT NOT NULL, " +
                COL_ATTENDANCE + " TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addNewSubtoSQL(SubjectPojo subjectData) {

        String sche = new Gson().toJson(subjectData.getSchedule());

        String ah = new Gson().toJson(subjectData.getAttendanceHistory());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_SUBCODE, subjectData.getSubjectCode());
        values.put(COL_SUBNAME, subjectData.getSubjectName());
        values.put(COL_SUBPROF, subjectData.getSubjectProF());
        values.put(COL_MINPER, subjectData.getMinPer());
        values.put(COL_SCHEDULE, sche);
        Log.d(TAG, "Schedule " + sche);
        values.put(COL_ATTENDANCE, ah);
        Log.d(TAG, "AH " + ah);

        long stat = db.insert(TABLE_NAME, null, values);
        db.close();

        Log.d(TAG, "Added new subject! status :"+stat);
    }

    public List<SubjectPojo> getAllDatafromSQL(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME ;

        List<SubjectPojo> subjects = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        Type listTypeSch = new TypeToken<List<SubjectSchedulePojo>>() {
        }.getType();
        Type listTypeAH = new TypeToken<List<AttendanceHistoryPojo>>() {
        }.getType();

        if(cursor.moveToFirst()){
            do{
                SubjectPojo pojo = new SubjectPojo(
                        cursor.getInt(cursor.getColumnIndex(COL_ID)),
                        cursor.getInt(cursor.getColumnIndex(COL_MINPER)),
                        cursor.getString(cursor.getColumnIndex(COL_SUBCODE)),
                        cursor.getString(cursor.getColumnIndex(COL_SUBNAME)),
                        cursor.getString(cursor.getColumnIndex(COL_SUBPROF)),
                        new Gson().fromJson(cursor.getString(cursor.getColumnIndex(COL_SCHEDULE)), listTypeSch),
                        new Gson().fromJson(cursor.getString(cursor.getColumnIndex(COL_ATTENDANCE)), listTypeAH));
                Log.d(TAG, "sche from table: "+cursor.getString(cursor.getColumnIndex(COL_SCHEDULE)));
                subjects.add(pojo);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return subjects;

    }

    public List<SubjectSchedulePojo> getSubTimingsfromSQL(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_SUBCODE + " LIKE '%" + code + "%'";

        String subTimingsJson;
        List<SubjectSchedulePojo> subTimings = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            subTimingsJson = cursor.getString(cursor.getColumnIndex(COL_SCHEDULE));
            Type listType = new TypeToken<List<SubjectSchedulePojo>>() {
            }.getType();
            subTimings = new Gson().fromJson(subTimingsJson, listType);
        } else {
            Log.d(TAG, "Cursor count is zero");
        }
        cursor.close();
        db.close();
        return subTimings;
    }

    public List<AttendanceHistoryPojo> getAttendanceHistoryfromSQL(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_SUBCODE + " LIKE '%" + code + "%'";

        String subAttHisJson;
        List<AttendanceHistoryPojo> subAttHis = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            subAttHisJson = cursor.getString(cursor.getColumnIndex(COL_ATTENDANCE));
            Type listType = new TypeToken<List<AttendanceHistoryPojo>>() {
            }.getType();
            subAttHis = new Gson().fromJson(subAttHisJson, listType);
        } else {
            Log.d(TAG, "Cursor count is zero");
        }
        cursor.close();
        db.close();
        return subAttHis;
    }

    public void updateSubject(SubjectPojo subjectData) {
        String sche = new Gson().toJson(subjectData.getSchedule());
        String ah = new Gson().toJson(subjectData.getAttendanceHistory());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_SUBCODE, subjectData.getSubjectCode());
        values.put(COL_SUBNAME, subjectData.getSubjectName());
        values.put(COL_SUBPROF, subjectData.getSubjectProF());
        values.put(COL_MINPER, subjectData.getMinPer());
        values.put(COL_SCHEDULE, sche);
        Log.d(TAG, "Schedule " + sche);
        values.put(COL_SCHEDULE, ah);
        Log.d(TAG, "AH " + ah);

        db.update(TABLE_NAME, values, COL_SUBCODE + " = " + subjectData.getSubjectCode(), null);
        db.close();
        Log.d(TAG, "Updated!!");
    }


}
