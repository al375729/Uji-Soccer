package com.example.ujisoccer.Standing;

import com.example.ujisoccer.Database.Team;
import com.example.ujisoccer.Database.TeamInStanding;

import java.util.List;

public interface InterfaceStanding {

    int getIdLiga();


    void llenarListView(List<TeamInStanding> response);

     void setEquipos(List<Team> equipos);

}
