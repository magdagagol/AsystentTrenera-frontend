package com.asystenttrenera_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.asystenttrenera_frontend.attendance.AttendanceActivity;
import com.asystenttrenera_frontend.group.Group;
import com.asystenttrenera_frontend.group.GroupService;
import com.asystenttrenera_frontend.group.GroupsActivity;
import com.asystenttrenera_frontend.message.MessageActivity;
import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantActivity;
import com.asystenttrenera_frontend.participant.ParticipantService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnZawodnicy;
    private Button btnGroups;
    private Button btnMessage;
    private Button btnAttendanceRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnZawodnicy = findViewById(R.id.btnParticipants);
        btnGroups = findViewById(R.id.btnGroups);
        btnMessage = findViewById(R.id.btnMessages);
        btnAttendanceRegister = findViewById(R.id.btnAttendanceRegister);

        btnZawodnicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParticipantService participantService = new ParticipantService(MainActivity.this);
                participantService.participantsObject(new ParticipantService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"Something wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(ArrayList<Participant> response) {
                        //Toast.makeText(MainActivity.this,"Response works" + response, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, ParticipantActivity.class);
                        intent.putExtra("participants", response);
                        startActivity(intent);
                    }
                });
            }
        });

        btnGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroupService groupService = new GroupService(MainActivity.this);
                groupService.groupsObject(new GroupService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"Something wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(ArrayList<Group> response) {
                        Intent intent = new Intent(MainActivity.this, GroupsActivity.class);
                        intent.putExtra("groups", response);
                        startActivity(intent);
                    }
                });


            }
        });

        btnAttendanceRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AttendanceActivity.class);
                startActivity(intent);
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });
    }

}