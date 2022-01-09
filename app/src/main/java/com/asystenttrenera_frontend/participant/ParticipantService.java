package com.asystenttrenera_frontend.participant;

import static com.android.volley.VolleyLog.TAG;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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
    String url = QUERY_FOR_PARTICIPANTS;

    public ParticipantService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(ArrayList<Participant> response);
    }

    // GET
    public void participantsObject(VolleyResponseListener volleyResponseListener){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Participant> participantList = new ArrayList<>();
                        try {
                            for(int i=0; i<response.length(); i++){ ;
                                JSONObject participant = response.getJSONObject(i);

                                JSONArray parents = participant.getJSONArray("enrolledParents");

                                ArrayList<Parent> parentsList = new ArrayList<>();
                                if(parents.length()>0){

                                    for(int j=0; j<parents.length(); j++){
                                        JSONObject p = parents.getJSONObject(j);

                                        parentsList.add(new Parent(
                                                p.getLong("id"),
                                                p.getString("name"),
                                                p.getString("surname"),
                                                p.getString("phoneNumber"),
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
                                    //parentsList.add(new Parent("Nie ma takiego rodzica"));
                                    participantList.add(new Participant(
                                            participant.getLong("id"),
                                            participant.getString("name"),
                                            participant.getString("surname"),
                                            participant.getString("yearOfBirth"),
                                            participant.getString("email"),
                                            participant.getString("phoneNumber"),
                                            parentsList
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

    // POST
    public JSONObject createObject(Participant participant){
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("name", participant.getName());
            jsonObject.put("surname", participant.getSurname());
            jsonObject.put("yearOfBirth", participant.getYearOfBirth());
            jsonObject.put("email", participant.getEmail());
            jsonObject.put("phoneNumber", participant.getPhoneNumber());
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void addParticipant(JSONObject object){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, QUERY_FOR_PARTICIPANTS, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    Log.d(TAG, response.toString());
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

    }
}
