package com.example.ujisoccer.MainActivity;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.ujisoccer.Database.League;
import com.example.ujisoccer.Model;

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

                }
                //Error
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.hideProgressBar();
                view.showError("Cannot connect the Internet");
            }
        });
    }

    private void leaguesAviable(List<League> response) {

        view.mostrarInfoLiga(response);
        view.hideProgressBar();
        view.showText();
    }
}

