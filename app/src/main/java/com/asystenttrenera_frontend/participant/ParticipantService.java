package com.asystenttrenera_frontend.participant;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.asystenttrenera_frontend.parent.Parent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParticipantService {
    public static final String QUERY_FOR_PARTICIPANTS = "http://10.0.2.2:8080/api/zawodnik";

    Context context;

    public ParticipantService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(ArrayList<Participant> response);
    }

    public void participantsObject(VolleyResponseListener volleyResponseListener){
        String url = QUERY_FOR_PARTICIPANTS;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Participant> participantList = new ArrayList<>();
                        try {
                            for(int i=0; i<response.length(); i++){ ;
                                JSONObject participant = response.getJSONObject(i);

                                JSONArray parents = participant.getJSONArray("enrolledParents");

                                System.out.println("################################ parent json array list: " + parents.length());
                                List<Parent> parentsList = new ArrayList<>();
                                if(parents.length()>0){

                                    for(int j=0; j<parents.length(); j++){
                                        JSONObject p = parents.getJSONObject(j);

                                        parentsList.add(new Parent(
                                                p.getLong("id"),
                                                p.getString("name"),
                                                p.getString("surname"),
                                                p.getInt("phoneNumber"),
                                                p.getString("email"),
                                                p.getBoolean("contactAgree")
                                        ));
                                    }
                                    participantList.add(new Participant(
                                            participant.getLong("id"),
                                            participant.getString("name"),
                                            participant.getString("surname"),
                                            participant.getString("yearOfBirth"),
                                            participant.getString("email"),
                                            participant.getString("phoneNumber"),
                                            parentsList
                                    ));
                                } else {
                                    parentsList.add(new Parent("Nie ma takiego rodzica"));
                                    participantList.add(new Participant(
                                            participant.getLong("id"),
                                            participant.getString("name"),
                                            participant.getString("surname"),
                                            participant.getString("yearOfBirth"),
                                            participant.getString("email"),
                                            participant.getString("phoneNumber")
                                    ));
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        volleyResponseListener.onResponse(participantList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Something wrong");
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);

    }

}
