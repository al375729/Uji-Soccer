package com.example.ujisoccer.Standing;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.ujisoccer.Database.Team;
import com.example.ujisoccer.Database.TeamInStanding;
import com.example.ujisoccer.Model;

import java.util.List;

public class PresenterStanding {

    private InterfaceStanding view;
    private static Model modelo;
    private List<Team> equipos;

    public void setEquipos(List<Team> equipos) {
        this.equipos = equipos;
    }

    public PresenterStanding(final InterfaceStanding view, final Model model) {
        this.view = view;

        final int ligaID = view.getIdLiga();
        model.ClasifiaciónAPI(ligaID, new Response.Listener<List<TeamInStanding>>() {

            @Override
            public void onResponse(List<TeamInStanding> response) {
                view.hideProgressBar();
                if(response.isEmpty()==false) {
                    view.llenarListView(response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.aviso();
                view.hideProgressBar();
                view.showError("Cannot connect the Internet");
            }
        });

        model.equiposBaseDeDatos(new Response.Listener<List<Team>>() {

            @Override
            public void onResponse(List<Team> response) {
                if(response.isEmpty()==false){
                    equipos=response;
                    view.setEquipos(response);
                }
                else {
                    equiposApi(model,ligaID);
                }
            }
        },ligaID);

    }


    private void equiposApi(final Model model, int ligaId){
        model.equiposApi(ligaId,new Response.Listener<List<Team>>() {
        @Override
        public void onResponse(List<Team> response) {
            if(response.isEmpty()==false){
                model.insertarEquipos(response);
                view.setEquipos(response);
            }

        }
    },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.showError("Cannot connect the Internet");
            }
        });



    }
}

