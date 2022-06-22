package com.asystenttrenera_frontend.attendance;

import static com.android.volley.VolleyLog.TAG;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.asystenttrenera_frontend.group.Group;
import com.asystenttrenera_frontend.group.GroupService;
import com.asystenttrenera_frontend.participant.MySingleton;
import com.asystenttrenera_frontend.participant.Participant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttendanceService {
    public static final String QUERY_FOR_ATTENDANCE = "http://10.0.2.2:8080/api/attendance";
    String url = QUERY_FOR_ATTENDANCE;
    Context context;

    public AttendanceService(Context context) { this.context = context; }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(ArrayList<Attendance> response);
    }

    public void getAttendance(VolleyResponseListener volleyResponseListener){
        JsonArrayRequest jsonArray = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Attendance> attendanceArrayList = new ArrayList<>();
                        Group group = null;
                        try {
                            for(int i=0; i< response.length(); i++){
                                JSONObject object = response.getJSONObject(i);
                                System.out.println("response.getJSONObject(i)" + response.getJSONObject(i));
                                Log.d("date ", object.get("date").toString());
                                String dateString = object.get("date").toString();
                                Date date = null;
                               try {
                                   date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(dateString);
                               } catch (ParseException e) {
                                   e.printStackTrace();
                               }
                                try {
                                    JSONObject jsonObjectGroup = object.getJSONObject("group");

                                    group = new Group(jsonObjectGroup.getLong("id"), jsonObjectGroup.getString("name"));
                                    Log.i("json", group.toString());

                                } catch (Exception e) {
                                    Log.i("exception", e.toString());
                                }
                                List<Participant> participantArrayList = new ArrayList<>();
                                try {
                                    JSONArray participants = object.getJSONArray("participants");

                                    for(int j=0; j<participants.length(); j++){
                                        JSONObject participant = participants.getJSONObject(j);
                                        participantArrayList.add(new Participant(
                                                participant.getLong("id"),
                                                participant.getString("name"),
                                                participant.getString("surname"),
                                                participant.getString("yearOfBirth")
                                        ));
                                    }
                                    Log.i("json participants", participants.toString());
                                    Log.i("json participants array list", participantArrayList.toString());

                                } catch (Exception e) {
                                    Log.i("exception", e.toString());
                                }

                                attendanceArrayList.add(new Attendance(
                                        object.getLong("id"),
                                        date,
                                        group,
                                        participantArrayList
                                ));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        volleyResponseListener.onResponse(attendanceArrayList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Something wrong in groups");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonArray);
    }

    //PUT
    public void addAttendance(Date date, Group group){
        JSONObject jsonObject= new JSONObject();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String dateStr = df.format(date);
        try {
            jsonObject.put("date", dateStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url+"/group/"+group.getId(), jsonObject, new Response.Listener<JSONObject>() {
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
    public void putAttendanceParticipant(Long attendanceId, Long participantId){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url+"/"+attendanceId+"/participant/"+participantId, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "assign attendance to participant works");
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "assign attendance to participant NOT works");
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    //DELETE
    public void deleteAttendance(Long id){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.DELETE, url+"/"+id, null, new Response.Listener<JSONObject>() {
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
