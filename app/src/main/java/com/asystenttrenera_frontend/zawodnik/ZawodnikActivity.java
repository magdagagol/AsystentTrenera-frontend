package com.asystenttrenera_frontend.zawodnik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
                System.out.println(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
       // MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);


        /*
        zawodnik.add(new Zawodnik(1L, "Adrian1", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(2L, "Badam2", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(3L, "Cdrian3", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(4L, "Ddrian4", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(5L, "Edrian5", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(6L, "Fdrian6", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(7L, "Gdrian7", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(8L, "Adrian8", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(9L, "Badam9", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(10L, "Cdrian10", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(11L, "Ddrian11", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(12L, "Edrian12", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(13L, "Fdrian13", "Nowy", "2015", "a@a", "535353"));
        zawodnik.add(new Zawodnik(14L, "Gdrian14", "Nowy", "2015", "a@a", "535353"));
        */

        adapter = new ZawodnikAdapter(this, zawodnik);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int index) {
        Toast.makeText(this, "Imie: " + zawodnik.get(index).getImie(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ZawodnikActivity.this, ZawodnikDetails.class );
        intent.putExtra("id", index);
        startActivity(intent);
    }
}
