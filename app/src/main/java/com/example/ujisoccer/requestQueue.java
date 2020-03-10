package com.example.ujisoccer;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class requestQueue{
        private static requestQueue instance;
        private RequestQueue requestQueue;
        private static Context ctx;

        private requestQueue(Context context) {
            ctx = context;
            requestQueue = getRequestQueue();


        }

        public static synchronized requestQueue getInstance(Context context) {
            if (instance == null) {
                instance = new requestQueue(context);
            }
            return instance;
        }

        public RequestQueue getRequestQueue() {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
            }
            return requestQueue;
        }

        public <T> void addToRequestQueue(Request<T> req) {
            getRequestQueue().add(req);
        }


}