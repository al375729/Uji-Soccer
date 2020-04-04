package com.example.ujisoccer.ExtraActivity;



import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.ujisoccer.Database.Team;
import com.example.ujisoccer.Model;

import com.example.ujisoccer.Standing.Player;

import java.util.List;

public class ExtraPresenter {

    private static Model modelo;
    private final ExtraActivity view;
    private List<Team> equipos;

    public void setEquipos(List<Team> equipos) {
        this.equipos = equipos;
    }

    public ExtraPresenter(final ExtraActivity view, final Model model) {
        this.view = view;

        int ligaID =view.getIdLiga();
        int equipoID=view.getIdEquipo();
        model.goleadoresApi(ligaID,equipoID, new Response.Listener<List<Player>>() {

            @Override
            public void onResponse(List<Player> response) {
                List<Player> lista = response;
                view.hideProgressBar();
                if(response.isEmpty()==false){
                    view.llenarListView(response);
                }
                    else view.aviso();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.hideProgressBar();
                view.showError("Cannot connect the Internet");
                view.showAviso();
            }
        });
    }


}
