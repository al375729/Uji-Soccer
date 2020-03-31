package com.example.ujisoccer;

import android.content.Context;

import com.android.volley.Response;
import com.example.ujisoccer.Database.League;
import com.example.ujisoccer.Database.Team;

import java.util.List;

public class Presenter {

    private Inter view;
    private static Model modelo;

    public Presenter(final Inter view, Model model) {
        this.view = view;

        model.ligasAPI(new Response.Listener<List<League>>() {
            @Override
            public void onResponse(List<League> response) {
                view.mostrarInfoLiga(response);
            }
        });
    }





    public static void obtenerDatosLiga(final Response.Listener listener) {

         Response.Listener listenerPresenter = new Response.Listener<List<League>>() {
            @Override
            public void onResponse(List<League> response) {
               if(response.isEmpty()==false){
                   modelo.insertarLigas(response);
                   listener.onResponse(response);

               }
               else  modelo.ligasAPI(listener);
            }
        };
        modelo.ligasBaseDeDatos(listenerPresenter);


        }
}

