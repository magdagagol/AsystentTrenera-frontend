package com.asystenttrenera_frontend.message;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantService;

import java.util.ArrayList;

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

                System.out.println("############################# Participants: " + participants.toString());
                adapter = new ParticipantAdapter(getActivity().getApplicationContext(), R.layout.custom_spinner_participant_adapter, participants);
                Spinner spinner = (Spinner) view.findViewById(R.id.spinner3);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getActivity(), participants.get(i).getPhoneNumber(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });

        return view;
    }
}