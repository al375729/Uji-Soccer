package com.example.ujisoccer.ExtraActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.ujisoccer.Database.Team;
import com.example.ujisoccer.Database.TeamInStanding;
import com.example.ujisoccer.Model;
import com.example.ujisoccer.R;
import com.example.ujisoccer.Standing.Adapter;
import com.example.ujisoccer.Standing.InterfaceStanding;
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
        Log.d("myTag","ID LIGA1 =" +String.valueOf(ligaID));
        Log.d("myTag", "ID EQUIPO1 ="+String.valueOf(equipoID));
        model.goleadoresApi(ligaID,equipoID, new Response.Listener<List<Player>>() {

            @Override
            public void onResponse(List<Player> response) {
                List<Player> lista = response;
                if(response.isEmpty()==false)view.llenarListView(response);
                    else view.aviso();
            }
        });
    }


}
