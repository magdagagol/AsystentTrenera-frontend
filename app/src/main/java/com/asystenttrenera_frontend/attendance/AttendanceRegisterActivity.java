package com.asystenttrenera_frontend.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.asystenttrenera_frontend.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AttendanceRegisterActivity extends AppCompatActivity {
    FloatingActionButton addAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_register_activity);
        getSupportActionBar().setTitle("Lista obecności");

        addAttendance = findViewById(R.id.add_attendance);
        addAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }

    private void openDialog() {
        Intent intent = new Intent(AttendanceRegisterActivity.this, AddAttendance.class);
        startActivity(intent);
    }
}
