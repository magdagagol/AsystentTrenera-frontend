package com.asystenttrenera_frontend.participant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;

import java.util.ArrayList;
import java.util.List;

public class ParticipantActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participant_activity);

        Toast.makeText(this,"This activity works fine ", Toast.LENGTH_LONG).show();

        Intent intent = getIntent();
        String participants = intent.getStringExtra("participants");

        Toast.makeText(this,"Participants from participant activity" + participants, Toast.LENGTH_LONG).show();

        recyclerView = findViewById(R.id.participantRecyclerView);
        //recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Participant> participantList = new ArrayList<>();

        Participant participant1 = new Participant("aaaa","aaaa", "aaaaa", "wwww","www");
        Participant participant2 = new Participant("bbb","bbb", "bbbb", "wwww","www");

        participantList.add(participant1);
        participantList.add(participant2);

        adapter = new ParticipantAdapter(this, participantList);
        recyclerView.setAdapter(adapter);



    }
}
