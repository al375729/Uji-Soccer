package com.example.ujisoccer.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ujisoccer.Database.League;
import com.example.ujisoccer.Database.LeaguesDao;
import com.example.ujisoccer.Database.Team;

@Database(entities ={ League.class, Team.class }, version =1)
public abstract class LeagueDatabase extends RoomDatabase {

    public abstract LeaguesDao getLeaguesDao();



}
