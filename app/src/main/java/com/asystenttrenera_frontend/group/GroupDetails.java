package com.asystenttrenera_frontend.group;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GroupDetails extends AppCompatActivity implements AddParticipantsToGroupDialog.AddGroupDialogListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Participant> participants;

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
                Toast.makeText(this, "Edytuj", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
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
        String groupName = intent.getStringExtra("groupName");
        participants = intent.getParcelableArrayListExtra("praticipants");

        Group p = (Group) intent.getParcelableExtra("test1");
        System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPP " + p.getParticipants().get(0).getName());

        addParticipantsFB = findViewById(R.id.addParticipantsFB);

        addParticipantsFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });







        /* TODO:
        jeżeli nie ma activity nie wyświetla komunikatu,
        wyświetla inne istniejące activity
         */
        Bundle bundle = intent.getExtras();

        Group test123 = (Group)bundle.getSerializable("test123");
        System.out.println("test123 " + test123.getParticipants().get(0).getName());
        Toast.makeText(this, "test123 " + test123, Toast.LENGTH_SHORT).show();


        System.out.println("participants" + participants);
        getSupportActionBar().setTitle(groupName);

        adapter = new ParticipantForGroupAdapter(participants);
        recyclerView.setAdapter(adapter);

    }

    private void openDialog() {
        AddParticipantsToGroupDialog addDialog = new AddParticipantsToGroupDialog();
        addDialog.show(getSupportFragmentManager(), "Dodaj");
    }

    @Override
    public void applyText(String groupName) {
        System.out.println(groupName);
    }
}