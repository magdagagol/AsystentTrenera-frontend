package com.asystenttrenera_frontend.zawodnik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.asystenttrenera_frontend.R;

public class ZawodnikDetails extends AppCompatActivity {
    TextView zawodnikDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zawodnik_details);

        zawodnikDetails = findViewById(R.id.btnDetails);

        int id = getIntent().getIntExtra("id", 0);

        zawodnikDetails.setText("id zawodnika " + id);
    }
}