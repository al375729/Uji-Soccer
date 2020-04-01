package com.example.ujisoccer;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.example.ujisoccer.Database.League;
import com.example.ujisoccer.Database.Team;

import java.util.List;

public class Presenter {

    private Inter view;
    private static Model modelo;


    public Presenter(final Inter view, final Model model) {
        this.view = view;

        model.ligasBaseDeDatos(new Response.Listener<List<League>>() {
            @Override
            public void onResponse(List<League> response) {
                if(response.isEmpty()==false) {
                    leaguesAviable(response);
                    Log.d("myTag", "ha llamado la base de datos");
                }
                   else getLeaguesFromAPI(model);
            }
        });
    }

    private void getLeaguesFromAPI(final Model model) {
        model.ligasAPI(new Response.Listener<List<League>>() {
            @Override
            public void onResponse(List<League> response) {
                if(response.isEmpty()==false) {
                    model.insertarLigas(response);
                    leaguesAviable(response);
                    Log.d("myTag", "ha llamado la API");

                }
                    //Error
            }
        });
    }

    private void leaguesAviable(List<League> response) {
        Log.d("myTag", "funciona");
        view.mostrarInfoLiga(response);
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

        public void infoLeagues(int id){}


}

