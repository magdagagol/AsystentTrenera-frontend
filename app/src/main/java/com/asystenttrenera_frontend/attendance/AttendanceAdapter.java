package com.asystenttrenera_frontend.attendance;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;

import java.util.ArrayList;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {
    ArrayList<Attendance> attendanceArrayList;
    ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked(int i);
    }

    public AttendanceAdapter(Context context, ArrayList<Attendance> attendanceArrayList) {
        this.attendanceArrayList = attendanceArrayList;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView date;
    TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.attendance_date);
            name = itemView.findViewById(R.id.attendance_name);

            //cardView = itemView.findViewById(R.id.physical_checkup_cv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("attendance get tag", String.valueOf(attendanceArrayList.indexOf(view.getTag())));
                    activity.onItemClicked(attendanceArrayList.indexOf(view.getTag()));
                }
            });
            //itemView.setOnCreateContextMenuListener(this);

        }
    }

    @NonNull
    @Override
    public AttendanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_reyecler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(attendanceArrayList.get(position));
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = df.format(attendanceArrayList.get(position).getDate());
        holder.date.setText(dateStr);
        holder.name.setText(attendanceArrayList.get(position).getGroup().getName());
    }

    @Override
    public int getItemCount() {
        return attendanceArrayList.size();
    }



}
