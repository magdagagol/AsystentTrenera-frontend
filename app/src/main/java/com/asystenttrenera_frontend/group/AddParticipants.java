package com.asystenttrenera_frontend.group;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantService;

import java.util.ArrayList;

public class AddParticipants extends AppCompatActivity implements CheckBoxAdapter.OnCheckedInfoListener {
    ArrayList<Participant> participants;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CheckBoxAdapter checkBoxAdapter;
    ArrayList<Participant> participantsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participants);

        recyclerView = findViewById(R.id.recycler_add_to_group);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String groupName = extras.getString("groupName");
            Long groupId = extras.getLong("groupId");

            ParticipantService participantService = new ParticipantService(AddParticipants.this);
            participantService.getParticipantsWithoutGroup(new ParticipantService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    System.out.println(message);
                }

                @Override
                public void onResponse(ArrayList<Participant> response) {
                    participants = response;

                    checkBoxAdapter = new CheckBoxAdapter(AddParticipants.this, participants);
                    recyclerView.setAdapter(checkBoxAdapter);

                    Button button = findViewById(R.id.updateGroupItem);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("Participants " + participantsArray);
                            Log.i("participants", participants.toString());

                            for (int i=0; i<participants.size(); i++) {
                                if(participantsArray.contains(participants.get(i))){
                                    participantService.addParticipantToGroup(participants.get(i).getId(), groupId);
                                } else {
                                    participantService.addParticipantToGroup(participants.get(i).getId(), null);
                                }
                            }
                            //finish();
                        }
                    });


                }
            }, groupId);


            getSupportActionBar().setTitle("Dodaj zawodników do grupy " + groupName);
        } else {
            finish();
        }
    }

    @Override
    public ArrayList<Participant> onCheckedInfoListener(Intent intent) {
        participantsArray = intent.getParcelableArrayListExtra("participants");
        return participantsArray;
    }
}