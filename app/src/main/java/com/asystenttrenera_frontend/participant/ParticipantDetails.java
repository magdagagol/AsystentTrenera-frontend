package com.asystenttrenera_frontend.participant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.EditText;

import com.asystenttrenera_frontend.R;

import java.util.ArrayList;

public class ParticipantDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_details);


        EditText addName = findViewById(R.id.addName);
        EditText addSurname = findViewById(R.id.addSurname);
        EditText addEmail = findViewById(R.id.addEmail);
        EditText addYearOfBirdth = findViewById(R.id.addYearOfBirdt);
        EditText addPhoneNumber = findViewById(R.id.addPhoneNumber);

        Intent intent = getIntent();
       // ArrayList<Parcelable> participant = intent.getParcelableArrayListExtra("details");

        Participant participant = intent.getParcelableExtra("details");

        addName.setText(participant.getName());
        addSurname.setText(participant.getSurname());
        addEmail.setText(participant.getEmail());
        addYearOfBirdth.setText(participant.getYearOfBirth());
        addPhoneNumber.setText(participant.getPhoneNumber());
    }
}