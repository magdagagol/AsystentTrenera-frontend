package com.asystenttrenera_frontend.group;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;

public class GroupsActivity extends AppCompatActivity implements AddGroupDialog.AddGroupDialogListener {
    private FloatingActionButton addGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        Intent intent = getIntent();
        ArrayList<Group> groupArrayList = intent.getParcelableArrayListExtra("groups");

        Toast.makeText(GroupsActivity.this, "groups form main activity: " + groupArrayList.get(1).getId(), Toast.LENGTH_SHORT).show();

        addGroup = findViewById(R.id.addGroup);

        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }

    private void openDialog() {
        AddGroupDialog addGroupDialog = new AddGroupDialog();
        addGroupDialog.show(getSupportFragmentManager(), "Dodaj");
    }

    @Override
    public void applyText(String groupName) {
        System.out.println("group name #############################: " + groupName);
        GroupService groupService = new GroupService(this);
        System.out.println("group service: " + groupService.getGroupName(groupName).toString());
        JSONObject object = groupService.getGroupName(groupName);
        groupService.postGroupObject(object);
    }
}