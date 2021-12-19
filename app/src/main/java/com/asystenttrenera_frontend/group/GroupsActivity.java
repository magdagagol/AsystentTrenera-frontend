package com.asystenttrenera_frontend.group;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;

import java.util.ArrayList;

public class GroupsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        ArrayList<Group> groupArrayList;

        Intent intent = getIntent();
        groupArrayList = intent.getParcelableArrayListExtra("groups");

        Toast.makeText(GroupsActivity.this, "groups form main activity: " + groupArrayList.get(1).getId(), Toast.LENGTH_SHORT).show();
    }
}