package com.asystenttrenera_frontend.participant;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParticipantService {
    public static final String QUERY_FOR_PARTICIPANTS = "http://10.0.2.2:8080/api/zawodnik";

    Context context;

    public ParticipantService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(JSONObject response);
    }

    public void participantStr(VolleyResponseListener volleyResponseListener){
        String url = QUERY_FOR_PARTICIPANTS;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject participant = null;
                        try {
                            participant = response.getJSONObject(0);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(participant);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "response don't works", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something wrong");
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);

    }

}
