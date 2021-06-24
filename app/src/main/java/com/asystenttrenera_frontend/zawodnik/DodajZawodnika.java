package com.asystenttrenera_frontend.zawodnik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.asystenttrenera_frontend.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DodajZawodnika extends AppCompatActivity {
    EditText zawodnikImie;
    EditText zawodnikNazwisko;
    EditText zawodnikRokUrodzenia;
    EditText zawodnikEmail;
    EditText zawodnikNumerTelefonu;
    Button dodajZawodnika;


    //Simple request using volley
    String url = "http://192.168.0.80:8080/api/zawodnik";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zawodnika);

        dodajZawodnika = findViewById(R.id.dodajZawodnika);

        dodajZawodnika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zawodnikImie = findViewById(R.id.zawodnikImie);
                String imie = zawodnikImie.getText().toString();

                zawodnikNazwisko = findViewById(R.id.zawodnikNazwisko);
                String nazwisko = zawodnikNazwisko.getText().toString();

                zawodnikRokUrodzenia = findViewById(R.id.zawodnikRokUrodzenia);
                String rokUrodzenia = zawodnikRokUrodzenia.getText().toString();

                zawodnikEmail = findViewById(R.id.zawodnikEmail);
                String email = zawodnikEmail.getText().toString();

                zawodnikNumerTelefonu = findViewById(R.id.zawodnikNumerTelefonu);
                String numerTelefonu = zawodnikNumerTelefonu.getText().toString();

                Zawodnik zawodnik = new Zawodnik(imie, nazwisko, rokUrodzenia, email, numerTelefonu);
                Toast.makeText(getBaseContext(), "Dodono nowego zawodnika", Toast.LENGTH_SHORT).show();

                HashMap<String, String> params = new HashMap<>();
                params.put("imie", imie);
                params.put("nazwisko", nazwisko);
                params.put("rokUrodzenia", rokUrodzenia);
                params.put("email", email);
                params.put("numerTelefonu", numerTelefonu);

                RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //
                                //
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Error: " + error.getMessage());
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;
                    }
                };
                requestQueue.add(jsonObjectRequest);
                onBackPressed();

            }
        });
    }
}