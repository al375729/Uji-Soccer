package com.example.ujisoccer;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface UserDao {

    @Insert
    void insertTeam(Team... teams);

    @Insert
    void insertLeague(League... leagues);

    @Query("SELECT * FROM League ORDER BY name")
    List<League> getAllLeagues();

    @Query("SELECT * FROM Team")
    List<Team> getAllLeague();
}