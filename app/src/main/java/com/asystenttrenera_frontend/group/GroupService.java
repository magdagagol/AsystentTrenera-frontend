package com.asystenttrenera_frontend.group;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.asystenttrenera_frontend.MainActivity;
import com.asystenttrenera_frontend.participant.MySingleton;
import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GroupService {
    public static final String QUERY_FOR_GROUPS = "http://10.0.2.2:8080/api/group";

    Context context;

    public GroupService(Context context) { this.context = context; }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(ArrayList<Group> response);
    }

    public void groupsObject(VolleyResponseListener volleyResponseListener){
        String url = QUERY_FOR_GROUPS;

        JsonArrayRequest jsonArray = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Group> groupArrayList = new ArrayList<>();
                        try {
                            ArrayList<Participant> participants = new ArrayList<>();
                            for(int i=0; i< response.length(); i++){
                                JSONObject object = response.getJSONObject(i);

                                JSONArray array = object.getJSONArray("participants");
                                for(int j=0; j<array.length(); j++){
                                    System.out.println(array.getJSONObject(j).getLong("id"));
                                    participants.add(new Participant(
                                            array.getJSONObject(j).getLong("id"),
                                            array.getJSONObject(j).getString("name"),
                                            array.getJSONObject(j).getString("surname"),
                                            array.getJSONObject(j).getString("yearOfBirth")
                                    ));
                                }

                                groupArrayList.add(new Group(
                                        object.getLong("id"),
                                        object.getString("name"),
                                        participants
                                ));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        volleyResponseListener.onResponse(groupArrayList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Something wrong in groups");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonArray);

    }

}
