package com.shambu.autoattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AttendanceRVadapter extends RecyclerView.Adapter {

    private static final String TAG = "AttendanceRVadapter";

    private AttendanceMarkingListener attendanceMarkingListener;
    private Context mContext;
    private List<SubjectPojo> allSubjects;

    public AttendanceRVadapter(AttendanceMarkingListener attendanceMarkingListener,
                               Context mContext, List<SubjectPojo> allSubjects) {
        this.attendanceMarkingListener = attendanceMarkingListener;
        this.mContext = mContext;
        this.allSubjects = allSubjects;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_rv_item, parent,
                false);

        return new AttendancePercentageHolder(view, attendanceMarkingListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        SubjectPojo pojo = allSubjects.get(position);
        ((AttendancePercentageHolder) holder).bind(pojo);

    }

    @Override
    public int getItemCount() {
        return allSubjects.size();
    }

    private class AttendancePercentageHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

        private AttendanceMarkingListener mListener;

        TextView subcode, subname, profname, percentage, classesfrac;
        ImageView present, noclass, absent;

        public AttendancePercentageHolder(@NonNull View itemView, AttendanceMarkingListener listener) {
            super(itemView);
            this.mListener = listener;
            subcode = itemView.findViewById(R.id.att_subcode_tv);
            subname = itemView.findViewById(R.id.att_subname_tv);
            profname = itemView.findViewById(R.id.att_profname_tv);
            percentage = itemView.findViewById(R.id.att_curr_percentage_tv);
            classesfrac = itemView.findViewById(R.id.att_classes_tv);
            present = itemView.findViewById(R.id.att_mark_present_imgv);
            noclass = itemView.findViewById(R.id.att_mark_noclass_imgv);
            absent = itemView.findViewById(R.id.att_mark_absent_imgv);

            present.setOnTouchListener(this);
            noclass.setOnTouchListener(this);
            absent.setOnTouchListener(this);

        }

        void bind(SubjectPojo pojo){
            if(pojo.getAttendanceHistory()!=null){
                int[] classcount = calcPer(pojo.getAttendanceHistory());
                subcode.setText(pojo.getSubjectCode());
                subname.setText(pojo.getSubjectName());
                profname.setText(pojo.getSubjectProF());
                percentage.setText(((float)((classcount[0]/classcount[1])*100))+"%");
                classesfrac.setText(classcount[0]+"/"+classcount[1]);
            }
            else {
                subcode.setText(pojo.getSubjectCode());
                subname.setText(pojo.getSubjectName());
                profname.setText(pojo.getSubjectProF());
                percentage.setText("0%");
                classesfrac.setText("0/0");
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mListener.markAttendanceClick(v, getAdapterPosition());

            return true;
        }
    }

    private int[] calcPer(List<AttendanceHistoryPojo> pojo){

        int totclass = 0, attendedclasses = 0;

        for( int i = 0 ; i < pojo.size() ; i++ ) {
            if(pojo.get(i).getClassHappened() && pojo.get(i).getAttendance()){
                totclass++;
                attendedclasses++;
            } else if(pojo.get(i).getClassHappened() && !pojo.get(i).getAttendance()){
                totclass++;
            }
        }

        return new int[]{attendedclasses, totclass};
    }


}
