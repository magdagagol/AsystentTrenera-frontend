package com.asystenttrenera_frontend.participant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asystenttrenera_frontend.MainActivity;
import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.group.GroupService;
import com.asystenttrenera_frontend.group.GroupsActivity;
import com.asystenttrenera_frontend.kyu.KyuActivity;
import com.asystenttrenera_frontend.parent.AddParentDialog;
import com.asystenttrenera_frontend.parent.Parent;
import com.asystenttrenera_frontend.parent.ParentAdapter;
import com.asystenttrenera_frontend.parent.ParentService;
import com.asystenttrenera_frontend.parent.ParentsListFrag;
import com.asystenttrenera_frontend.physicalCheckup.PhysicalCheckupActivity;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDetails extends AppCompatActivity implements EditParticipantDialog.EditParticipantDialogListener,
        AddParentDialog.AddParentDialogListener {
    Participant participant;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.participant_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.parent:
                addParent();
                break;
            case R.id.edit:
                editParticipant(participant);
                Toast.makeText(this, "Edycja zawodnika", Toast.LENGTH_SHORT).show();
                break;
            case R.id.physicalCheckup:
                Intent intent = new Intent(ParticipantDetails.this, PhysicalCheckupActivity.class);
                intent.putExtra("participant", participant);
                startActivity(intent);
                break;
            case R.id.kyu:
                Intent intentKyu = new Intent(ParticipantDetails.this, KyuActivity.class);
                intentKyu.putExtra("participant", participant);
                startActivity(intentKyu);
                break;
            case R.id.delete:
                ParticipantService participantService = new ParticipantService(this);
                participantService.deleteGroupObject(participant.getId());
                Toast.makeText(this, "Zawodnik został usunięty", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        participant = intent.getParcelableExtra("details");

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("parents", (ArrayList<? extends Parcelable>) participant.getParents());
        bundle.putLong("participant_id", participant.getId());

        Log.i("parents+", participant.toString());

        ParentsListFrag parentsListFrag = new ParentsListFrag();
        parentsListFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, parentsListFrag)
                .commit();

        setContentView(R.layout.activity_participant_details);
        //String activityTitle = participant.getName() + " " + participant.getSurname();
        getSupportActionBar().setTitle("Szczegóły zawodnika");
        //getSupportActionBar().setSubtitle(activityTitle);

        TextView addName = findViewById(R.id.addName);
        TextView addSurname = findViewById(R.id.addSurname);
        TextView addEmail = findViewById(R.id.addEmail);
        TextView addYearOfBirth = findViewById(R.id.addYearOfBirth);
        TextView addPhoneNumber = findViewById(R.id.addPhoneNumber);

        addName.setText(participant.getName());
        addSurname.setText(participant.getSurname());
        addEmail.setText(participant.getEmail());
        addYearOfBirth.setText(participant.getYearOfBirth());
        addPhoneNumber.setText(participant.getPhoneNumber());

        List<Parent> p = participant.getParents();
        ArrayList<Parent> parentArrayList = new ArrayList<>();
        parentArrayList.addAll(p);

    }

    private void editParticipant(Participant participant) {
        EditParticipantDialog editParticipantDialog = new EditParticipantDialog();
        Bundle args = new Bundle();
        args.putParcelable("participant", participant);
        editParticipantDialog.setArguments(args);
        editParticipantDialog.show(getSupportFragmentManager(), "addPhysicalCheckupDialog");
    }

    @Override
    public void updateTexts(Long id, String name, String surname, String birth, String email, String phone) {
        Participant participant = new Participant(name, surname, birth, email, phone);
        ParticipantService participantService = new ParticipantService(ParticipantDetails.this);
        participantService.updateParticipant(participant, id);
    }

    @Override
    public void applyText(String name, String surname, String phone, String email, Boolean agree) {
        Parent parent = new Parent(name, surname, phone, email, agree);
        ParentService parentService = new ParentService(ParticipantDetails.this);
        parentService.addParent(parent, participant.getId());

    }

    private void addParent() {
        AddParentDialog addParentDialog = new AddParentDialog();
        addParentDialog.show(getSupportFragmentManager(), "addParentDialog");
    }
}