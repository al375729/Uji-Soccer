package com.example.ujisoccer.Standing;

import android.util.Log;

import com.android.volley.Response;
import com.example.ujisoccer.Database.League;
import com.example.ujisoccer.Database.Team;
import com.example.ujisoccer.Database.TeamInStanding;
import com.example.ujisoccer.Inter;
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
                List<TeamInStanding> lista = response;
                String name = response.get(0).name;
                Log.d("myTag", name);
                view.llenarListView(response);
            }
        });

        model.equiposBaseDeDatos(new Response.Listener<List<Team>>() {

            @Override
            public void onResponse(List<Team> response) {
                if(response.isEmpty()==false){
                    equipos=response;
                    view.setEquipos(response);
                    Log.d("myTag", "EQUIPOSBD");
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
            List<Team> equipos = response;
            Log.d("myTag", "EQUIPOSAPI");
            model.insertarEquipos(response);
            view.setEquipos(response);


            //Error
        }
    });



    }
}

