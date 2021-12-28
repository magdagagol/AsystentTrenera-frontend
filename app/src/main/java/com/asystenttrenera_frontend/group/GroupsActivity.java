package com.asystenttrenera_frontend.group;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;

public class GroupsActivity extends AppCompatActivity implements AddGroupDialog.AddGroupDialogListener, GroupAdapter.ItemClicked {
    private FloatingActionButton addGroup;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Group> groupArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        getSupportActionBar().setTitle("Grupy");

        recyclerView = findViewById(R.id.groupsList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        groupArrayList = intent.getParcelableArrayListExtra("groups");

        adapter = new GroupAdapter(this, groupArrayList);
        recyclerView.setAdapter(adapter);



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
        GroupService groupService = new GroupService(this);
        JSONObject object = groupService.getGroupName(groupName);
        groupService.postGroupObject(object);
    }

    @Override
    public void onItemClicked(int index) {
        Toast.makeText(this, "aaaaa" + groupArrayList.get(index).getParticipants() + "# " + groupArrayList.get(index).getId(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, GroupDetails.class);

        String groupName = groupArrayList.get(index).getName();
        intent.putExtra("groupName", groupName);

        intent.putExtra("praticipants",groupArrayList.get(index).getParticipants());
        System.out.println("Groups activity  ********************************************* " +groupArrayList.get(index).getId());
        System.out.println("Groups activity  ********************************************* " +groupArrayList.get(index).getName());
        System.out.println("Groups activity  ********************************************* " + groupArrayList.get(index).getParticipants().toString());

        intent.putExtra("test1", (Parcelable) groupArrayList.get(index));
        Bundle bundle = new Bundle();
        bundle.putSerializable("test123", groupArrayList.get(index));
        intent.putExtras(bundle);

        startActivity(intent);
    }
}