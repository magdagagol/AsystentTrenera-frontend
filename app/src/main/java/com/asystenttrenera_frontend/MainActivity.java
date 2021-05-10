package com.asystenttrenera_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.asystenttrenera_frontend.zawodnik.ZawodnikActivity;

public class MainActivity extends AppCompatActivity {
    Button btnZawodnicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnZawodnicy = findViewById(R.id.btnZawodnicy);

        btnZawodnicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ZawodnikActivity.class);
                startActivity(intent);
            }
        });

    }

}