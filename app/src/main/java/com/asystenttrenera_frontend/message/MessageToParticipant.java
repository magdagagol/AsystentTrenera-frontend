package com.asystenttrenera_frontend.message;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MessageToParticipant extends Fragment {
    ArrayList<Participant> participants;
    ParticipantAdapter adapter;
    Button saveButton;
    EditText message;

   @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message_to_participant, container, false);

        saveButton = view.findViewById(R.id.saveMessageToParticipant);
        message = view.findViewById(R.id.messageToParticipant);

        ParticipantService participantService = new ParticipantService(getActivity().getApplicationContext());
        participantService.participantsObject(new ParticipantService.VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ArrayList<Participant> response) {
                participants = response;

                adapter = new ParticipantAdapter(getActivity().getApplicationContext(), R.layout.custom_spinner_participant_adapter, participants);
                Spinner spinner = (Spinner) view.findViewById(R.id.spinner3);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        saveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                MessageActivity messageActivity = (MessageActivity) getActivity();
                                Date dateAndTime = messageActivity.getCurrentDateAndTime();
                                Calendar calender = Calendar.getInstance();
                                calender.setTime(dateAndTime);

                                String phone = participants.get(i).getPhoneNumber();
                                String text = String.valueOf(message.getText());

                                startAlarm(calender, phone, text);
                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });
        return view;
    }

    private void startAlarm(Calendar c, String phone, String text) {
        final int requestCode = 1337;
        AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        intent.putExtra("phone", phone);
        intent.putExtra("text", text);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am.set( AlarmManager.RTC_WAKEUP, c.getTimeInMillis() , pendingIntent );
    }
}