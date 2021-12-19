package com.asystenttrenera_frontend.participant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.parent.Parent;
import com.asystenttrenera_frontend.parent.ParentAdapter;
import com.asystenttrenera_frontend.parent.ParentsListFrag;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("some_int", 0);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerView, ParentsListFrag.class, null)
                    .commit();
        }


        setContentView(R.layout.activity_participant_details);
        //String activityTitle = participant.getName() + " " + participant.getSurname();
        getSupportActionBar().setTitle("Szczegóły zawodnika");
        //getSupportActionBar().setSubtitle(activityTitle);

        TextView addName = findViewById(R.id.addName);
        TextView addSurname = findViewById(R.id.addSurname);
        TextView addEmail = findViewById(R.id.addEmail);
        TextView addYearOfBirth = findViewById(R.id.addYearOfBirth);
        TextView addPhoneNumber = findViewById(R.id.addPhoneNumber);

        Intent intent = getIntent();
       // ArrayList<Parcelable> participant = intent.getParcelableArrayListExtra("details");

        Participant participant = intent.getParcelableExtra("details");

        addName.setText(participant.getName());
        addSurname.setText(participant.getSurname());
        addEmail.setText(participant.getEmail());
        addYearOfBirth.setText(participant.getYearOfBirth());
        addPhoneNumber.setText(participant.getPhoneNumber());

        List<Parent> p = participant.getParents();
        ArrayList<Parent> parentArrayList = new ArrayList<>();
        parentArrayList.addAll(p);



        System.out.println("############################ parent details" + participant.getParents().toString());
        System.out.println("############################ parent details list" + p.toString());

        Bundle bundle = new Bundle();
        ArrayList<Parent> parent = (ArrayList<Parent>) participant.getParents();
        System.out.println("########## array list parents form participant details " + parentArrayList.get(0).getName());
        //bundle.putParcelableArrayList("parent", parent);

       bundle.putString("test", "444444444444444444444444From Activity");
        // set Fragmentclass Arguments
        Fragment fragobj = new ParentsListFrag(parent);
        fragobj.setArguments(bundle);
        bundle.putParcelableArrayList("parent", parent);



        Button button = findViewById(R.id.buttonAddParent);

    }
}