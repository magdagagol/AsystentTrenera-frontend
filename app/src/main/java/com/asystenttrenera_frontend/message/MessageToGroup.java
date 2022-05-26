package com.asystenttrenera_frontend.message;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.group.Group;
import com.asystenttrenera_frontend.group.GroupService;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MessageToGroup extends Fragment {
    ArrayList<Group> groups;
    GroupAdapter groupAdapter;
    CheckBox checkBoxParticipants, checkBoxParents, checkBoxAll;
    Button saveMessageToGroup;
    String checked;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_to_group, container, false);

        checkBoxParticipants = view.findViewById(R.id.checkBoxParticipant);
        checkBoxParents = view.findViewById(R.id.checkBoxParents);
        checkBoxAll = view.findViewById(R.id.checkBoxAll);

        saveMessageToGroup = view.findViewById(R.id.saveMessageToGroup);
        GroupService groupService = new GroupService(getActivity().getApplicationContext());
        groupService.groupsObject(new GroupService.VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ArrayList<Group> response) {
                groups = response;
                groupAdapter = new GroupAdapter(getActivity().getApplicationContext(), groups);
                Spinner spinner = (Spinner) view.findViewById(R.id.spinner2);
                spinner.setAdapter(groupAdapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Group group = (Group) adapterView.getItemAtPosition(i);
                        saveMessageToGroup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Spinner spinner = (Spinner) view.findViewById(R.id.spinner2);
                                if(checkBoxParents.isChecked()){
                                    //checkBoxParents.setChecked(true);
                                    checkBoxParticipants.setChecked(false);
                                    checkBoxAll.setChecked(false);
                                    checked = "parents";
                                } else if (checkBoxParticipants.isChecked()){
                                    checkBoxParents.setChecked(false);
                                    //checkBoxParticipants.setChecked(false);
                                    checkBoxAll.setChecked(false);
                                    checked = "participants";
                                } else if (checkBoxAll.isChecked()){
                                    checkBoxParents.setChecked(false);
                                    checkBoxParticipants.setChecked(false);
                                    // checkBoxAll.setChecked(false);
                                    checked = "all";
                                }
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
}