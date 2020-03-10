package com.example.ujisoccer;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;


public class Queries  {


    private static Context ctx;
    String url="https://api.football-data.org/v2/";

    RequestQueue queue = requestQueue.getInstance(ctx.getApplicationContext()).
            getRequestQueue();



    JsonArrayRequest request = new JsonArrayRequest(
            Request.Method.GET, // The method: GET, POST, ...
            url, // The url
            null, // Additional JSON information
            listener, errorListener // The listeners
    );

 queue.addToRequestQueue(request);


}
