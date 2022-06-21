package com.asystenttrenera_frontend.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity implements AttendanceAdapter.ItemClicked {
    FloatingActionButton addAttendance;
    ArrayList<Attendance> attendanceArrayList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Attendance currentAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_activity);
        getSupportActionBar().setTitle("Lista obecności");

        recyclerView = findViewById(R.id.attendance_recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        addAttendance = findViewById(R.id.add_attendance);
        addAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        AttendanceService attendanceService = new AttendanceService(this);
        attendanceService.getAttendance(new AttendanceService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @Override
            public void onResponse(ArrayList<Attendance> response) {
                attendanceArrayList = response;
                TextView textView = findViewById(R.id.isAttendance);

                if(attendanceArrayList.isEmpty()){
                    textView.setText("Brak listy obecności");
                } else {
                    adapter = new AttendanceAdapter(AttendanceActivity.this, attendanceArrayList);
                    recyclerView.setAdapter(adapter);
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void openDialog() {
        Intent intent = new Intent(AttendanceActivity.this, AddAttendance.class);
        startActivity(intent);
    }


    @Override
    public void onItemClicked(int i) {
        currentAttendance = attendanceArrayList.get(i);

        Intent intent = new Intent(this, AttendanceDetails.class);
        //int position = attendanceArrayList.get(i);
        Attendance attendance = attendanceArrayList.get(i);
        intent.putExtra("details", (Parcelable) attendance);
        startActivity(intent);
    }
}
