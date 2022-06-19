package com.asystenttrenera_frontend.message;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.asystenttrenera_frontend.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MessageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
   private int STORAGE_PERMISSION_CODE = 1;

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


        if(ContextCompat.checkSelfPermission( MessageActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MessageActivity.this, "Fail sent message", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "Permission error");
            requestStoragePermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == STORAGE_PERMISSION_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MessageActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MessageActivity.this, "No permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestStoragePermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

            new AlertDialog.Builder(this).setTitle("Permissio")
                    .setMessage("Do działania aplikacja potrzebuje zgody na dostęp do managera wiadomości")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(MessageActivity.this, new String[] {Manifest.permission.SEND_SMS}, STORAGE_PERMISSION_CODE);
                }
            }).setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            }).create().show();

        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, STORAGE_PERMISSION_CODE);
        }
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

        Log.i("message", "information from message activity");
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

    public Date getCurrentDateAndTime(){
        TextView date = findViewById(R.id.datePickerTV);
        TextView time = findViewById(R.id.dateTimePickerTV);

        String dtStart = (String) date.getText() + " " + time.getText();
        Log.i("date", dtStart);

        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss aa");
        Date date2 = null;
        try {
            date2 = format.parse(dtStart);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date2;
    }
}

