package com.asystenttrenera_frontend.parent;

import static android.content.ContentValues.TAG;

import android.content.Context;
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

import java.util.ArrayList;

public class ParentService {
    private static String QUERY_FOR_PARENT = "http://10.0.2.2:8080/api/parent";

    Context context;
    String url = QUERY_FOR_PARENT;

    public ParentService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(ArrayList<Parent> response);
    }

    //GET
    public void getParents(VolleyResponseListener volleyResponseListener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Parent> parentArrayList = new ArrayList<>();
                try {
                    for (int i=0; i< response.length(); i++){
                        JSONObject object = response.getJSONObject(i);
                        parentArrayList.add(new Parent(
                                object.getLong("id"),
                                object.getString("name"),
                                object.getString("surName"),
                                object.getString("phoneNumber"),
                                object.getString("email"),
                                object.getBoolean("contactAgree")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(parentArrayList);
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
    public void addParent (Parent parent, Long id) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("name", parent.getName());
            jsonObject.put("surname", parent.getSurname());
            jsonObject.put("phoneNumber", parent.getPhoneNumber());
            jsonObject.put("email", parent.getEmail());
            jsonObject.put("contactAgree", parent.getContactAgree());

        }catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url+"/zawodnik/"+id, jsonObject, new Response.Listener<JSONObject>() {
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
    public void updateParent (Parent parent, Long id) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("name", parent.getName());
            jsonObject.put("surname", parent.getSurname());
            jsonObject.put("phoneNumber", parent.getPhoneNumber());
            jsonObject.put("email", parent.getEmail());
            jsonObject.put("contactAgree", parent.getContactAgree());

        }catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url+"/"+id, jsonObject, new Response.Listener<JSONObject>() {
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
    public void deleteParent (Long id){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url+"/"+id, null, new Response.Listener<JSONObject>() {
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
