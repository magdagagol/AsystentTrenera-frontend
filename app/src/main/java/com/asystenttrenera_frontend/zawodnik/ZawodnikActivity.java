package com.asystenttrenera_frontend.zawodnik;

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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.asystenttrenera_frontend.MainActivity;
import com.asystenttrenera_frontend.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ZawodnikActivity extends AppCompatActivity implements ZawodnikAdapter.ItemClicked {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Zawodnik> zawodnik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zawodnik);

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        zawodnik = new ArrayList<Zawodnik>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Simple request using volley
        String url = "http://192.168.0.80:8080/api/zawodnik";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Log.i("res", response.toString());
                try {
                    for(int i=0; i<response.length(); i++){
                        JSONObject responseObj = response.getJSONObject(i);
                        Long id = responseObj.getLong("id");
                        String imie = responseObj.getString("imie");
                        String nazwisko = responseObj.getString("nazwisko");
                        String rokUrodzenia = responseObj.getString("rokUrodzenia");
                        String email = responseObj.getString("email");
                        String numerTelefonu = responseObj.getString("numerTelefonu");

                        zawodnik.add(new Zawodnik(id, imie, nazwisko, rokUrodzenia, email, numerTelefonu));
                        //System.out.println(zawodnik);
                        adapter = new ZawodnikAdapter(ZawodnikActivity.this, zawodnik);
                        recyclerView.setAdapter(adapter);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onItemClicked(int index) {
        Toast.makeText(this, "Imie: " + zawodnik.get(index).getImie(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ZawodnikActivity.this, ZawodnikDetails.class );
        int id = zawodnik.get(index).getId().intValue();
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.lista_zawodnikow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ZawodnikActivity.this, DodajZawodnika.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
