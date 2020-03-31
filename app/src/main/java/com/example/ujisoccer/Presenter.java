package com.example.ujisoccer;

import android.content.Context;

import com.android.volley.Response;
import com.example.ujisoccer.Database.League;

import java.util.List;

public class Presenter {

    private MainActivity vistaInicial;
    private static Model modelo;

    public Presenter(MainActivity vistaInicial, Context context) {
        this.vistaInicial = vistaInicial;
        this.modelo = Model.getInstance(context);
    }

    public static void obtenerDatosLiga(final Response.Listener listener) {

         Response.Listener listenerPresenter = new Response.Listener<List<League>>() {
            @Override
            public void onResponse(List<League> response) {
               if(!response.isEmpty()) listener.onResponse(response);
               else  modelo.ligasAPI(listener);
            }
        };
        modelo.ligasBaseDeDatos(listenerPresenter);


        }
}

