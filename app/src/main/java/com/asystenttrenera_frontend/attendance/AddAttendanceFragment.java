package com.asystenttrenera_frontend.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.group.CheckBoxAdapter;
import com.asystenttrenera_frontend.group.Group;
import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantService;

import java.util.ArrayList;

public class AddAttendanceFragment extends Fragment implements CheckBoxFragmentAdapter.OnCheckedInfoListener {
    View view;
    Group group;
    ArrayList<Participant> participants;
    ArrayList<Participant> participantsArray;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    int testA;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_attendance, container, false);
        Group group = getArguments().getParcelable("group");
        recyclerView = view.findViewById(R.id.recycler_add_attendance);
        //setRecyclerView();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        Button button = getActivity().findViewById(R.id.add_attendance_button);
        if(group != null){
            ParticipantService participantService = new ParticipantService(getActivity());
            participantService.getParticipantsWithGroup(new ParticipantService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    System.out.println(message);
                }

                @Override
                public void onResponse(ArrayList<Participant> response) {
                    participants = response;
                    adapter = new CheckBoxFragmentAdapter(AddAttendanceFragment.this, participants);
                    recyclerView.setAdapter(adapter);
                    if (participants.isEmpty()){
                        Log.d("attendance", "no participant in this group");
                    } else {
                        System.out.println("Participant attendance " + participants.toString());


                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("clicked item form fragment", participantsArray.toString());

                            }
                        });

                    }
                }
            }, group.getId());
            Log.i("group attendance fragment", group.toString());
        }


        return view;
    }

    @Override
    public ArrayList<Participant> onCheckedInfoListener(ArrayList<Participant> p) {
        participantsArray = p;
       // return participantsArray;
        Log.d("participants array from add attendance fragment", p.toString());
        return participantsArray;
    }

    private void addAttendance(){

    }
}
