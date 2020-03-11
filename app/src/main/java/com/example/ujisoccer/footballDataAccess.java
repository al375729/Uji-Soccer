package com.example.ujisoccer;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class footballDataAccess {


    private static Context ctx;
    String url="https://api.football-data.org/v2/";

    RequestQueue queue;

    public footballDataAccess(Context context) {


        queue = Volley.newRequestQueue(context);
    }






 public JSONArray[] queri(String url){


        final JSONArray[] answer = new JSONArray[1];

        JsonArrayRequest request = new JsonArrayRequest(
             Request.Method.GET, // The method: GET, POST, ...
             url, // The url
             null, // Additional JSON information

             new Response.Listener<JSONArray>() {
                 @Override
                 public void onResponse(JSONArray response) {
                     answer[0] =response;
                 }},

             new Response.ErrorListener(){
                 @Override
                 public void onErrorResponse(VolleyError error){}}
     )

     {
         /** Passing some request headers* */
         @Override
         public Map<String, String> getHeaders() {
             Map<String, String> headers = new HashMap<>();
             headers.put("X-Auth-Token", "d24a05e231994ca18ad7a0512e1d4c9d");
             return headers;
         }
     };

     queue.add(request);
    return answer;
 }

}
