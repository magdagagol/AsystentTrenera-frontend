package com.asystenttrenera_frontend.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.group.Group;
import com.asystenttrenera_frontend.group.GroupService;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        getSupportActionBar().setTitle("Wysyłanie wiadomości");

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.choose, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }


    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Fragment messageToParticipant;
        Fragment messageToGroup;

        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this, "Spinner works with: " + text, Toast.LENGTH_SHORT).show();

        messageToParticipant = new MessageToParticipant();
        messageToGroup = new MessageToGroup();

        if(text.equals("Do zawodnika")){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.message_to, messageToParticipant)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.message_to, messageToGroup)
                    .commit();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

