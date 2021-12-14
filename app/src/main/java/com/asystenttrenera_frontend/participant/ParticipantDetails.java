package com.asystenttrenera_frontend.participant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.asystenttrenera_frontend.R;

public class ParticipantDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_details);
        //String activityTitle = participant.getName() + " " + participant.getSurname();
        getSupportActionBar().setTitle("Szczegóły zawodnika");
        //getSupportActionBar().setSubtitle(activityTitle);

        TextView addName = findViewById(R.id.addName);
        TextView addSurname = findViewById(R.id.addSurname);
        TextView addEmail = findViewById(R.id.addEmail);
        TextView addYearOfBirth = findViewById(R.id.addYearOfBirth);
        TextView addPhoneNumber = findViewById(R.id.addPhoneNumber);

        Intent intent = getIntent();
       // ArrayList<Parcelable> participant = intent.getParcelableArrayListExtra("details");

        Participant participant = intent.getParcelableExtra("details");

        addName.setText(participant.getName());
        addSurname.setText(participant.getSurname());
        addEmail.setText(participant.getEmail());
        addYearOfBirth.setText(participant.getYearOfBirth());
        addPhoneNumber.setText(participant.getPhoneNumber());

        participant.getParents();
        System.out.println("############################ " + participant.getParents().toString());
    }
}