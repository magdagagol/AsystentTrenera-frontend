package com.asystenttrenera_frontend.attendance;

import android.app.DatePickerDialog;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.group.Group;
import com.asystenttrenera_frontend.group.GroupService;
import com.asystenttrenera_frontend.message.DatePickerFragment;
import com.asystenttrenera_frontend.message.GroupAdapter;
import com.asystenttrenera_frontend.participant.Participant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddAttendance extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button chooseDate;
    Button addAttendance;
    GroupAdapter groupAdapter;
    ArrayList<Group> groupArrayList;
    Group group;
    ArrayList<Participant> participants;
    Date myDate;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.add_attendance);
        getSupportActionBar().setTitle("Nowa lista obecnośći");

        addAttendance = findViewById(R.id.add_attendance_button);
        chooseDate = findViewById(R.id.add_attendance_date);

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

                Spinner spinner = findViewById(R.id.add_attendance_list_spinner);
                TextView textView = findViewById(R.id.add_attandance_is_group);

                if (groupArrayList.isEmpty()){
                    textView.setText("Żadna grupa nie została jeszcze utworzona");
                    spinner.setVisibility(View.INVISIBLE);
                } else {
                    groupAdapter = new GroupAdapter(AddAttendance.this, groupArrayList);
                    spinner.setAdapter(groupAdapter);
                    textView.setVisibility(View.INVISIBLE);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            group = (Group) adapterView.getItemAtPosition(i);
                           // getParticipantFromGroup(group);
                            addAttendance.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Log.d("add attendance date", myDate.toString());
                                    addNewAttendance(myDate , group);
                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

            }
        });
    }

    private void getParticipantFromGroup(Group group) {
        Log.i("group", group.toString());

        Bundle bundle = new Bundle();
        bundle.putParcelable("group", group);
        AttendanceDetails fragment = new AttendanceDetails();
       // fragment.setArguments(bundle);

      //  getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout1, fragment)
       //         .commit();

    }

    private void showDatePickerDialog(View view) {
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
        TextView datePickerTV = findViewById(R.id.add_attendance_show_date);
        myDate = c.getTime();
        datePickerTV.setText(currentDate);
    }

    public void addNewAttendance(Date date, Group group){
        AttendanceService attendanceService = new AttendanceService(this);
        attendanceService.addAttendance(date, group);
    }

}

