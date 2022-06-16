package com.asystenttrenera_frontend.participant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.parent.Parent;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.ArrayList;

public class AddParticipant extends AppCompatActivity {
    EditText addParticipantName, addParticipantSurname, addParticipantYearOfBirth,
            addParticipantEmail, addParticipantPhoneNumber;
    Button addNewParticipant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participant);
        getSupportActionBar().setTitle("Dodawanie nowego zawodnika");

        addParticipantName = findViewById(R.id.addParticipantName);
        addParticipantSurname = findViewById(R.id.addParticipantSurname);
        addParticipantYearOfBirth = findViewById(R.id.addParticipantYearOfBirth);
        addParticipantEmail = findViewById(R.id.addParticipantEmail);
        addParticipantPhoneNumber = findViewById(R.id.addParticipantPhoneNumber);

        addNewParticipant = findViewById(R.id.addNewParticipant);

        addNewParticipant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Participant participant = new Participant(
                        addParticipantName.getText().toString(),
                        addParticipantSurname.getText().toString(),
                        addParticipantYearOfBirth.getText().toString(),
                        addParticipantEmail.getText().toString(),
                        addParticipantPhoneNumber.getText().toString()
                );

                ParticipantService participantService = new ParticipantService(AddParticipant.this);
                //JSONObject object = participantService.createObject(participant);
                participantService.addParticipant(participant);

                Toast.makeText(AddParticipant.this, "Zawodnik zosatł dodany", Toast.LENGTH_SHORT).show();
                AddParticipant.this.finish();
            }
        });
    }
}