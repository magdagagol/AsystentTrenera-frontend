package com.asystenttrenera_frontend.physicalCheckup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class PhysicalCheckupActivity extends AppCompatActivity implements AddPhysicalCheckupDialog.AddPhysicalCheckupDialogListener,
        PhysicalCheckupAdapter.ItemClicked, EditPhysicalCheckupDialog.EditPhysicalCheckupDialogListener, DeletePhysicalCheckupDialog.DeletePhysicalCheckupDialogListener {
    ArrayList<PhysicalCheckup> physicalCheckups;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton addPhysicalCheckup;
    Long participant_id;

    private PhysicalCheckup currentPhysicalCheckup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_checkup);

        Intent intent = getIntent();
        Participant participant = intent.getParcelableExtra("participant");

        recyclerView = findViewById(R.id.physical_checkup_rv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getSupportActionBar().setTitle("Badania");
        getSupportActionBar().setSubtitle(participant.getName() + " " + participant.getSurname());

        addPhysicalCheckup = findViewById(R.id.addPhysicalCheckup);
        addPhysicalCheckup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPhysicalCheckup();
            }
        });

        PhysicalCheckupService physicalCheckupService = new PhysicalCheckupService(PhysicalCheckupActivity.this);
        participant_id = participant.getId();
        physicalCheckupService.physicalCheckupWithParticipant(new PhysicalCheckupService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @Override
            public void onResponse(ArrayList<PhysicalCheckup> response) {
                physicalCheckups = response;
                TextView textView = findViewById(R.id.isPhysicalCheckup);

                if (physicalCheckups.isEmpty()){
                    textView.setText("Zawodnik nie ma dodanych badań");
                } else {
                    adapter = new PhysicalCheckupAdapter(PhysicalCheckupActivity.this, physicalCheckups);
                    recyclerView.setAdapter(adapter);
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        }, participant_id);
    }

    private void addPhysicalCheckup() {
        AddPhysicalCheckupDialog addPhysicalCheckupDialog = new AddPhysicalCheckupDialog();
        addPhysicalCheckupDialog.show(getSupportFragmentManager(), "addPhysicalCheckupDialog");
    }

    @Override
    public void applyTexts(Date date, Double height, Double weight, String comment) {
        PhysicalCheckup physicalCheckup = new PhysicalCheckup(date, height, weight, comment);
        PhysicalCheckupService physicalCheckupService = new PhysicalCheckupService(PhysicalCheckupActivity.this);
        physicalCheckupService.newPhysicalCheckup(physicalCheckup, participant_id);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 121:
                Log.i("item", "Usuń " + currentPhysicalCheckup.toString());
                deletePhysicalCheckup(currentPhysicalCheckup.getId());
                return true;
            case 122:
                Log.i("item", "Edytuj " + currentPhysicalCheckup.toString());
                editPhysicalCheckup(currentPhysicalCheckup);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onItemClicked(int index) {
        currentPhysicalCheckup = physicalCheckups.get(index);
    }

    private void editPhysicalCheckup(PhysicalCheckup physicalCheckup) {
        EditPhysicalCheckupDialog editPhysicalCheckupDialog = new EditPhysicalCheckupDialog();
        Bundle args = new Bundle();
        args.putParcelable("checkup", physicalCheckup);
        editPhysicalCheckupDialog.setArguments(args);
        editPhysicalCheckupDialog.show(getSupportFragmentManager(), "addPhysicalCheckupDialog");
    }

    @Override
    public void updateTexts(Long id, Date data, Double height, Double weight, String comment) {
        PhysicalCheckup physicalCheckup = new PhysicalCheckup(data, height, weight, comment);
        PhysicalCheckupService physicalCheckupService = new PhysicalCheckupService(PhysicalCheckupActivity.this);
        physicalCheckupService.updatePhysicalCheckup(physicalCheckup, id);
    }

    private void deletePhysicalCheckup(Long id) {
        DeletePhysicalCheckupDialog deletePhysicalCheckupDialog = new DeletePhysicalCheckupDialog();
        Bundle args = new Bundle();
        args.putLong("checkup_id", id);
        deletePhysicalCheckupDialog.setArguments(args);
        deletePhysicalCheckupDialog.show(getSupportFragmentManager(), "deletePhysicalCheckupDialog");
    }

    @Override
    public void deleteTexts(Long id) {
        PhysicalCheckupService physicalCheckupService = new PhysicalCheckupService(PhysicalCheckupActivity.this);
        physicalCheckupService.deletePhysicalCheckup(id);
    }
}
