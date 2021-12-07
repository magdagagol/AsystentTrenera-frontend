package com.asystenttrenera_frontend.participant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;

import java.util.ArrayList;

public class ParticipantActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Participant> participants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participants_activity);

        //recyclerView = findViewById(R.id.participant_recyclerView);
        //recyclerView.setHasFixedSize(true);

        //layoutManager = new LinearLayoutManager(this);

        //adapter = new ParticipantAdapter(this, participants);
        //recyclerView.setAdapter(adapter);

        Toast.makeText(this,"This activity works fine ", Toast.LENGTH_LONG).show();

        Intent intent = getIntent();
        String participants = intent.getStringExtra("participants");

        Toast.makeText(this,"Participants from participant activity" + participants, Toast.LENGTH_LONG).show();

    }
}
