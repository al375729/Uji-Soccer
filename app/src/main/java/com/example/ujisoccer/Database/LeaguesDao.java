package com.example.ujisoccer.Database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ujisoccer.Database.League;

import java.util.List;

@Dao
public abstract class LeaguesDao {

    @Insert
    public abstract void insertarLigas(List<League> leagues);

    @Query("SELECT * FROM leagues ORDER BY name")
    public abstract List<League> infoLigas();

    @Insert
    public abstract void insertarEquipos(List<Team> teams);

    @Query("SELECT * FROM Team WHERE league_id == (:id) ")
    public abstract List<Team> infoEquipos(Integer id);



}
