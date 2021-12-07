package com.asystenttrenera_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.asystenttrenera_frontend.participant.MySingleton;
import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantActivity;
import com.asystenttrenera_frontend.participant.ParticipantService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnZawodnicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnZawodnicy = findViewById(R.id.btnParticipants);

        btnZawodnicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParticipantService participantService = new ParticipantService(MainActivity.this);
                participantService.participantsObject(new ParticipantService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"Something wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(ArrayList<Participant> response) {
                        Toast.makeText(MainActivity.this,"Response works" + response, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, ParticipantActivity.class);
                        intent.putExtra("participants", response);
                        startActivity(intent);

                    }
                });
            }
        });

    }

}