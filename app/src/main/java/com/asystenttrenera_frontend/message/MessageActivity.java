package com.asystenttrenera_frontend.message;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.TimeZoneFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.asystenttrenera_frontend.R;
import com.google.android.material.timepicker.TimeFormat;

import java.text.DateFormat;
import java.util.Calendar;

public class MessageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
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
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);

        String currentTime = DateFormat.getTimeInstance().format(c.getTime());
        TextView datePickerTV = findViewById(R.id.dateTimePickerTV);
        datePickerTV.setText(currentTime);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);

        String currentDate = DateFormat.getDateInstance().format(c.getTime());
        TextView datePickerTV = findViewById(R.id.datePickerTV);
        datePickerTV.setText(currentDate);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

