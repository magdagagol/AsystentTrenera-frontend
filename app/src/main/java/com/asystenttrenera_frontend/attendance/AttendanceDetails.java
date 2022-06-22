package com.asystenttrenera_frontend.attendance;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.group.GroupService;
import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantService;

import java.util.ArrayList;

public class AttendanceDetails extends AppCompatActivity implements CheckBoxAdapter.OnCheckedInfoListener, DeleteAttendanceDialog.DeleteAttendanceDialogListener {
    Attendance attendance;
    ArrayList<Participant> participants;
    ArrayList<Participant> participantsArray = null;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.attendance_details, menu);
        return true;
    }

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_details);

        Intent intent = getIntent();
        attendance = intent.getParcelableExtra("details");

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = df.format(attendance.getDate());
        getSupportActionBar().setTitle( dateStr + " - " + attendance.getGroup().getName());

        Log.d("attendance group", attendance.toString());
        recyclerView = findViewById(R.id.recycler_add_attendance);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button button = findViewById(R.id.button22);
        AttendanceService attendanceService = new AttendanceService(this);

        if(attendance != null){
            ParticipantService participantService = new ParticipantService(this);
            participantService.getParticipantsWithGroup(new ParticipantService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    System.out.println(message);
                }

                @Override
                public void onResponse(ArrayList<Participant> response) {
                    participants = response;
                    adapter = new CheckBoxAdapter(AttendanceDetails.this, participants, attendance);
                    recyclerView.setAdapter(adapter);
                    if (participants.isEmpty()){
                        Log.d("attendance", "no participant in this group");
                    } else {
                        System.out.println("Participant attendance " + participants.toString());


                       button.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               for (int i=0; i<participants.size(); i++) {
                                   if(participantsArray.contains(participants.get(i))){
                                       attendanceService.putAttendanceParticipant(attendance.getId(), participants.get(i).getId());
                                   } else {
                                      // participantService.addParticipantToGroup(participants.get(i).getId(), null);
                                   }
                               }
                           }
                       });
                    }
                }
            }, attendance.getGroup().getId());
            Log.i("group attendance fragment", attendance.getParticipants().toString());

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_attendance:
                Log.i("item", "Usuń " + attendance.getDate().toString() +
                        " " + attendance.getGroup().getName());
                deleteAttendance(attendance.getId());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAttendance(Long id) {
        DeleteAttendanceDialog deleteAttendanceDialog = new DeleteAttendanceDialog();
        Bundle args = new Bundle();
        args.putLong("attendance_id", id);
        deleteAttendanceDialog.setArguments(args);
        deleteAttendanceDialog.show(getSupportFragmentManager(), "deleteAttendanceDialog");
    }

    @Override
    public ArrayList<Participant> onCheckedInfoListener(Intent intent) {
        participantsArray = intent.getParcelableArrayListExtra("participants");
        return participantsArray;
    }

    @Override
    public void deleteTexts(Long id) {
        AttendanceService attendanceService = new AttendanceService(AttendanceDetails.this);
        attendanceService.deleteAttendance(id);
        Toast.makeText(this, "Lista została usunieta", Toast.LENGTH_SHORT).show();
    }
}
