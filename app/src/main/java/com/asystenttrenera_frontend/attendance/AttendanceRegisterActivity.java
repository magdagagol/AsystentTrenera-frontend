package com.asystenttrenera_frontend.attendance;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.group.Group;
import com.asystenttrenera_frontend.group.GroupService;
import com.asystenttrenera_frontend.message.DatePickerFragment;
import com.asystenttrenera_frontend.message.GroupAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AttendanceRegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button chooseDate;
    GroupAdapter groupAdapter;
    ArrayList<Group> groupArrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_register_activity);
        getSupportActionBar().setTitle("Lista obecności");

        chooseDate = findViewById(R.id.chooseDate);
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });

        GroupService groupService = new GroupService(this);
        groupService.groupsObject(new GroupService.VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ArrayList<Group> response) {
                groupArrayList = response;
                Spinner spinner = findViewById(R.id.attendance_list_spinner);
                groupAdapter = new GroupAdapter(AttendanceRegisterActivity.this, groupArrayList);
                spinner.setAdapter(groupAdapter);
            }
        });



        //
        //
        //groupAdapter = new GroupAdapter(getActivity().getApplicationContext(), groups);
        //Spinner spinner = (Spinner) view.findViewById(R.id.spinner2);
        //spinner.setAdapter(groupAdapter);
        //spinner.setOnItemSelectedListener(this);

    }


    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);

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
        TextView datePickerTV = findViewById(R.id.attendance_list_show_date);
        datePickerTV.setText(currentDate);
    }

    public Date getCurrentDateAndTime(){
        TextView date = findViewById(R.id.attendance_list_show_date);

        String dtStart = (String) date.getText();

        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        Date date2 = null;
        try {
            date2 = format.parse(dtStart);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date2);
        return date2;
    }
}
