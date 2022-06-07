package com.asystenttrenera_frontend.kyu;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class KyuService {
    private static String QUERY_FOR_KYU = "http://10.0.2.2:8080/api/kyu";

    Context context;
    String url = QUERY_FOR_KYU;

    public KyuService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(ArrayList<Kyu> response);
    }

    //GET
    public void kyuWithParticipant(VolleyResponseListener volleyResponseListener, Long participant_id) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url + "/withParticipant/" + participant_id, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Kyu> physicalCheckupArrayList = new ArrayList<>();
                try {
                    for (int i=0; i< response.length(); i++){
                        JSONObject object = response.getJSONObject(i);
                        String dateString = object.get("examDate").toString();
                        Date date = null;
                        try {
                            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        physicalCheckupArrayList.add(new Kyu(
                                object.getLong("id"),
                                date,
                                object.getString("kyuDegree")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
    public void newKyu (Kyu kyu, Long id){
        JSONObject jsonObject = new JSONObject();
        Date dateT = kyu.getExamDate();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(dateT);

        try {
            jsonObject.put("examDate", date);
            jsonObject.put("kyuDegree", kyu.getKyuDegree());
        } catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url+"/zawodnik/"+id, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    //PUT
    public void updateKyu (Kyu kyu, Long id){
        JSONObject jsonObject = new JSONObject();
        Date dateT = kyu.getExamDate();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(dateT);

        try {
            jsonObject.put("examDate", date);
            jsonObject.put("kyuDegree", kyu.getKyuDegree());
        } catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url+"/"+id, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    //DELETE
    public void deleteKyu (Long id){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url+"/"+id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

}
