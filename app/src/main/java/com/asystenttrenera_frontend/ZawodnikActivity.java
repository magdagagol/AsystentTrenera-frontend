package com.asystenttrenera_frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

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

        adapter = new ZawodnikAdapter(this, zawodnik);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int index) {
        Toast.makeText(this, "Imie: " + zawodnik.get(index).getImie(), Toast.LENGTH_SHORT).show();
    }
}
