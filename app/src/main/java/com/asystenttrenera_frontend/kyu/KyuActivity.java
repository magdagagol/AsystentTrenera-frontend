package com.asystenttrenera_frontend.kyu;

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
import com.asystenttrenera_frontend.physicalCheckup.DeletePhysicalCheckupDialog;
import com.asystenttrenera_frontend.physicalCheckup.EditPhysicalCheckupDialog;
import com.asystenttrenera_frontend.physicalCheckup.PhysicalCheckup;
import com.asystenttrenera_frontend.physicalCheckup.PhysicalCheckupActivity;
import com.asystenttrenera_frontend.physicalCheckup.PhysicalCheckupAdapter;
import com.asystenttrenera_frontend.physicalCheckup.PhysicalCheckupService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class KyuActivity extends AppCompatActivity implements AddKyuDialog.AddKyuDialogListener,
        EditKyuDialog.EditKyuDialogListener, DeleteKyuDialog.DeleteKyuDialogListener, KyuAdapter.ItemClicked {
    ArrayList<Kyu> kyuArrayList;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton floatingActionButton;
    Long participant_id;

    private Kyu currentKyu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyu);

        Intent intent = getIntent();
        Participant participant = intent.getParcelableExtra("participant");

        recyclerView = findViewById(R.id.kyu_rv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getSupportActionBar().setTitle("Stopnie Kyu");
        getSupportActionBar().setSubtitle(participant.getName() + " " + participant.getSurname());

        floatingActionButton = findViewById(R.id.addKyu);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addKyu();
            }
        });

        KyuService kyuService = new KyuService(KyuActivity.this);
        participant_id = participant.getId();
        kyuService.kyuWithParticipant(new KyuService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @Override
            public void onResponse(ArrayList<Kyu> response) {
                kyuArrayList = response;
                TextView textView = findViewById(R.id.isKyu);

                if (kyuArrayList.isEmpty()){
                    textView.setText("Zawodnik nie zdobył stopni kyu");
                } else {
                    adapter = new KyuAdapter(KyuActivity.this, kyuArrayList);
                    recyclerView.setAdapter(adapter);
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        }, participant_id);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 121:
                Log.i("item", "Usuń " + currentKyu.toString());
                deleteKyu(currentKyu.getId());
                return true;
            case 122:
                Log.i("item", "Edytuj " + currentKyu.toString());
                editKyu(currentKyu);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onItemClicked(int index) {
        currentKyu = kyuArrayList.get(index);
    }

    private void addKyu() {
        AddKyuDialog addKyuDialog = new AddKyuDialog();
        addKyuDialog.show(getSupportFragmentManager(), "addKyuDialog");
    }

    @Override
    public void applyTexts(Date examDate, String kyuDegree) {
        Kyu kyu = new Kyu(examDate, kyuDegree);
        KyuService kyuService = new KyuService(KyuActivity.this);
        kyuService.newKyu(kyu, participant_id);
    }

    private void editKyu(Kyu kyu) {
        EditKyuDialog editKyuDialog = new EditKyuDialog();
        Bundle args = new Bundle();
        args.putParcelable("kyu", kyu);
        editKyuDialog.setArguments(args);
        editKyuDialog.show(getSupportFragmentManager(), "addPhysicalCheckupDialog");
    }

    @Override
    public void updateTexts(Long id, Date examDate, String kyuDegree) {
        Kyu kyu = new Kyu(id, examDate, kyuDegree);
        KyuService kyuService = new KyuService(KyuActivity.this);
        kyuService.updateKyu(kyu, id);
    }

    private void deleteKyu(Long id) {
        DeleteKyuDialog deleteKyuDialog = new DeleteKyuDialog();
        Bundle args = new Bundle();
        args.putLong("kyu_id", id);
        deleteKyuDialog.setArguments(args);
        deleteKyuDialog.show(getSupportFragmentManager(), "deleteKyuDialog");
    }

    @Override
    public void deleteTexts(Long id) {
        KyuService kyuService = new KyuService(KyuActivity.this);
        kyuService.deleteKyu(id);
    }

}
