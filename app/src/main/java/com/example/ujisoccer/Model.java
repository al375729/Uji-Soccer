package com.example.ujisoccer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.ujisoccer.Database.League;
import com.example.ujisoccer.Database.LeagueDatabase;
import com.example.ujisoccer.Database.LeaguesDao;
import com.example.ujisoccer.Database.Team;
import com.example.ujisoccer.Database.TeamInStanding;
import com.example.ujisoccer.Standing.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Model {

 private static final Integer[] idsLeaguesToFollow =
         {2021, 2015, 2002, 2019, 2003, 2017, 2014};

 private static final Set<Integer> leaguesToFollow =
         new HashSet<>(Arrays.asList(idsLeaguesToFollow));

 private static Model instance;
 private static LeaguesDao dao;
 private static FootballDataAccess dataAccess;
 private final LeagueDatabase database;

 private Model(final Context context) {

  database = Room.databaseBuilder(context, LeagueDatabase.class, "futbolInfo").build();
  dao = database.getLeaguesDao();
  dataAccess = new FootballDataAccess(context);

 }

 public static synchronized Model getInstance(Context context) {
  if (instance == null) instance = new Model(context);
  return instance;
 }

 public void ligasBaseDeDatos(Response.Listener<List<League>> listener) {
  new extraerLigasDeLaBaseDeDatos(listener).execute();
 }

 public void equiposBaseDeDatos(Response.Listener<List<Team>> listener, int id) {
  new extraerEquiposDeLaBaseDeDatos(listener, id).execute();
 }

 private static class extraerLigasDeLaBaseDeDatos extends AsyncTask<Void, Void, List<League>> {

  private Response.Listener<List<League>> listener;

  private extraerLigasDeLaBaseDeDatos(Response.Listener<List<League>> listener) {
   this.listener = listener;
  }

  @Override
  protected List<League> doInBackground(Void... voids) {
   return dao.infoLigas();
  }

  @Override
  protected void onPostExecute(List<League> leagues) {
   listener.onResponse(leagues);
  }

 }


 private static class extraerEquiposDeLaBaseDeDatos extends AsyncTask<Void, Void, List<Team>> {
  private final int id;

  private Response.Listener<List<Team>> listener;

  private extraerEquiposDeLaBaseDeDatos(Response.Listener<List<Team>> listener, int id) {
   this.listener = listener;
   this.id =id;
  }

  @Override
  protected List<Team> doInBackground(Void... voids) {
   return dao.infoEquipos(id);
  }

  @Override
  protected void onPostExecute(List<Team> teams) {
   listener.onResponse(teams);
  }

 }

 public void ligasAPI(Response.Listener<List<League>> listener, Response.ErrorListener errorListener) {





  dataAccess.getLeagues(leaguesToFollow, listener, errorListener);

 }


 public void ClasifiaciónAPI(int ligaId, Response.Listener<List<TeamInStanding>> listener, Response.ErrorListener errorListener) {

  dataAccess.getStandings(ligaId, listener, errorListener);

 }


 public void insertarLigas(List<League> ligas) {
  new insertarLigasEnLaBaseDeDatos(ligas).execute();
 }

 private static class insertarLigasEnLaBaseDeDatos extends AsyncTask<List<League>, Void, Void> {
  private List<League> ligas;


  private insertarLigasEnLaBaseDeDatos(List<League> ligas) {
   this.ligas = ligas;
  }


  @Override
  protected Void doInBackground(List<League>... lists) {
   dao.insertarLigas(ligas);
   return null;
  }

 }

 public void equiposApi(int ligaId, Response.Listener<List<Team>> listener, Response.ErrorListener errorListener) {

  dataAccess.getTeams(ligaId, listener, errorListener);

 }



    public void insertarEquipos(List<Team> equipos) {

        new insertarEquiposEnLaBaseDeDatos(equipos).execute();

    }

    private static class insertarEquiposEnLaBaseDeDatos extends AsyncTask<List<Team>, Void, Void> {
        private List<Team> equipos;


        private insertarEquiposEnLaBaseDeDatos(List<Team> equipos) {
            this.equipos = equipos;
        }


        @Override
        protected Void doInBackground(List<Team>... lists) {
            dao.insertarEquipos(equipos);
            return null;
        }

    }


 public void goleadoresApi(int ligaId, int equipoId, Response.Listener<List<Player>> listener, Response.ErrorListener errorListener) {

  dataAccess.getScorers(equipoId,ligaId, listener, errorListener);

 }
}