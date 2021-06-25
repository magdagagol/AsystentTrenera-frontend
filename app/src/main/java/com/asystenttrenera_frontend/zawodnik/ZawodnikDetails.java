package com.asystenttrenera_frontend.zawodnik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.asystenttrenera_frontend.R;

import org.json.JSONArray;

public class ZawodnikDetails extends AppCompatActivity {
    TextView zawodnikDetails;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zawodnik_details);

        zawodnikDetails = findViewById(R.id.btnDetails);

        id = getIntent().getIntExtra("id", 0);

        zawodnikDetails.setText("id zawodnika " + id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.szczegoly_zawodnika_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.usunZawodnika:
                Toast.makeText(this, "usuń", Toast.LENGTH_SHORT).show();
                System.out.println("sssssssssssss " + id);
                String url = "http://192.168.0.80:8080/api/zawodnik"+id;
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
                );

                return true;
            case R.id.edytujZawodnika:Zawodnika:
                Toast.makeText(this, "edytuj", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}