package com.asystenttrenera_frontend.physicalCheckup;


import static com.android.volley.VolleyLog.TAG;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.asystenttrenera_frontend.participant.MySingleton;
import com.asystenttrenera_frontend.participant.Participant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class PhysicalCheckupService {
    private static String QUERY_FOR_PHYSICAL_CHECKUP = "http://10.0.2.2:8080/api/physicalCheckup";

    Context context;
    String url = QUERY_FOR_PHYSICAL_CHECKUP;

    public PhysicalCheckupService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(ArrayList<PhysicalCheckup> response);
    }

    //GET
    public void physicalCheckupWithParticipant(VolleyResponseListener volleyResponseListener, Long participant_id) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url + "/withParticipant/" + participant_id, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<PhysicalCheckup> physicalCheckupArrayList = new ArrayList<>();
                try {
                    for (int i=0; i< response.length(); i++){
                        JSONObject object = response.getJSONObject(i);
                        String dateString = object.get("physicalCheckupData").toString();
                        Date date = null;
                        try {
                            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        physicalCheckupArrayList.add(new PhysicalCheckup(
                                object.getLong("id"),
                                date,
                                object.getDouble("height"),
                                object.getDouble("weight"),
                                object.getString("comment")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Date date = new Date();
                    physicalCheckupArrayList.add(new PhysicalCheckup(date, 2.2, 3.3, "ssssss"));
                }
                volleyResponseListener.onResponse(physicalCheckupArrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Something wrong");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }

    //PUT
    public void newPhysicalCheckup (PhysicalCheckup physicalCheckup, Long id){
        JSONObject jsonObject = new JSONObject();
        Date dateT = physicalCheckup.getPhysicalCheckupData();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(dateT);

        try {
            jsonObject.put("physicalCheckupData", date);
            jsonObject.put("height", physicalCheckup.getHeight());
            jsonObject.put("weight", physicalCheckup.getWeight());
            jsonObject.put("comment", physicalCheckup.getComment());
        } catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url+"/zawodnik/"+id,
                jsonObject, new Response.Listener<JSONObject>() {
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

    //PUT
    public void updatePhysicalCheckup (PhysicalCheckup physicalCheckup, Long id){
        JSONObject jsonObject = new JSONObject();
        Date dateT = physicalCheckup.getPhysicalCheckupData();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(dateT);

        try {
            jsonObject.put("physicalCheckupData", date);
            jsonObject.put("height", physicalCheckup.getHeight());
            jsonObject.put("weight", physicalCheckup.getWeight());
            jsonObject.put("comment", physicalCheckup.getComment());
        } catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, QUERY_FOR_PHYSICAL_CHECKUP+"/"+id, jsonObject, new Response.Listener<JSONObject>() {
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

    //DELETE
    public void deletePhysicalCheckup (Long id){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, QUERY_FOR_PHYSICAL_CHECKUP+"/"+id, null, new Response.Listener<JSONObject>() {
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
