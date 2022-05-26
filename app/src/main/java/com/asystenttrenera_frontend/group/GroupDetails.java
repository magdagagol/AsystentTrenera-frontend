package com.asystenttrenera_frontend.group;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantService;
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
                openDialog(group);
                break;
            case R.id.delete:
                GroupService groupService = new GroupService(this);
                groupService.deleteGroupObject(group.getId());
                Toast.makeText(this, "Grupa została usunięta", Toast.LENGTH_SHORT).show();
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

        getSupportActionBar().setTitle(group.getName());

        ParticipantService participantService = new ParticipantService(GroupDetails.this);
        Long group_id = group.getId();
        participantService.getParticipantsWithGroup(new ParticipantService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @Override
            public void onResponse(ArrayList<Participant> response) {
                Log.i("group", response.toString());
                participants = response;
                TextView textView = findViewById(R.id.textView2);

                if (participants.isEmpty()){
                    textView.setText("Grupa nie ma jeszcze zawodników");
                } else {
                    adapter = new ParticipantForGroupAdapter(participants);
                    Log.i("group2", participants.toString());
                    recyclerView.setAdapter(adapter);
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        }, group_id);

        addParticipantsFB = findViewById(R.id.addParticipantsFB);

        addParticipantsFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(GroupDetails.this, AddParticipants.class);
                newIntent.putExtra("groupName", group.getName());
                newIntent.putExtra("groupId", group.getId());
                startActivity(newIntent);
            }
        });

    }

   private void openDialog(Group group) {
        EditGroupDialog editGroupDialog = new EditGroupDialog();
        Bundle bundle = new Bundle();
        bundle.putString("groupName", group.getName());
        bundle.putLong("groupId", group.getId());
        editGroupDialog.setArguments(bundle);
        editGroupDialog.show(getSupportFragmentManager(), "Edytuj");
    }

    @Override
    public void applyText(String groupName, Long id){
        GroupService groupService = new GroupService(this);
        JSONObject object = groupService.getGroupName(groupName);
        groupService.putGroupObject(id, object);

        Toast.makeText(this, "Nazwa grupy została zmieniona", Toast.LENGTH_SHORT).show();
    }
}