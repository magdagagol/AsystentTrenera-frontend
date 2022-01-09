package com.asystenttrenera_frontend.group;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;

public class GroupDetails extends AppCompatActivity implements EditGroupDialog.AddGroupDialogListener {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Participant> participants;
    Group group;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.participant_in_group_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                Toast.makeText(this, "Edytuj"+ group.getId(), Toast.LENGTH_SHORT).show();
                openDialog();
                break;
            case R.id.delete:
                GroupService groupService = new GroupService(this);
                groupService.deleteGroupObject(group.getId());
                Toast.makeText(this, "Grupa została usunięta, widok nie odświeża się automatycznie", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        recyclerView = findViewById(R.id.partInGroupRV);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FloatingActionButton addParticipantsFB;

        Intent intent = getIntent();
        group = (Group) intent.getParcelableExtra("group");

        participants = group.getParticipants();
        getSupportActionBar().setTitle(group.getName());

        adapter = new ParticipantForGroupAdapter(participants);
        recyclerView.setAdapter(adapter);

        addParticipantsFB = findViewById(R.id.addParticipantsFB);

        addParticipantsFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(GroupDetails.this, AddParticipants.class);
                startActivity(newIntent);
            }
        });

    }

   private void openDialog() {
        EditGroupDialog editGroupDialog = new EditGroupDialog();
        editGroupDialog.show(getSupportFragmentManager(), "Edytuj");
    }

    @Override
    public void applyText(String groupName) {
        GroupService groupService = new GroupService(this);
        JSONObject object = groupService.getGroupName(groupName);
        groupService.putGroupObject(object);
    }
}