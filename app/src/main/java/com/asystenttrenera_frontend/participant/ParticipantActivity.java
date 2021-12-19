package com.asystenttrenera_frontend.participant;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ParticipantActivity extends AppCompatActivity implements ParticipantAdapter.ItemClicked {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Participant> participantList;
    FloatingActionButton addParticipant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participant_activity);
        getSupportActionBar().setTitle("Lista zawodników");

        Intent intent = getIntent();
        participantList = intent.getParcelableArrayListExtra("participants");


        recyclerView = findViewById(R.id.participantRecyclerView);
        //recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ParticipantAdapter(this, participantList);
        recyclerView.setAdapter(adapter);

        addParticipant = findViewById(R.id.addParticipant);

    }

    @Override
    public void onItemClicked(int index) {
        Intent intent = new Intent(this, ParticipantDetails.class);
        int i = participantList.indexOf(index);
        Participant participant = participantList.get(index);
        //intent.putExtra("details", participant);
        intent.putExtra("details", participant);
        startActivity(intent);
    }
}
