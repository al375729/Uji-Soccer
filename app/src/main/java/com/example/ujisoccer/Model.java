package com.example.ujisoccer;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.ujisoccer.Database.League;
import com.example.ujisoccer.Database.LeagueDatabase;
import com.example.ujisoccer.Database.LeaguesDao;
import com.example.ujisoccer.Database.Team;

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

 private Model(final Context context) {//Constructor privado porque queremos que el modelo siga el patron Singleton

  database = Room.databaseBuilder(context, LeagueDatabase.class, "futbolInfo").build();
  dao = database.getLeaguesDao();
  dataAccess = new FootballDataAccess(context);

 }

 public static synchronized Model getInstance(Context context) {//Como el constructor es privado llamaremos a esta funcion que o nos devovlera la isntancia del Modelo o si no existe llamara al constructor privado para crear una instancia
  if (instance == null) instance = new Model(context);
  return instance;
 }

 //Añadir las clases que extienden de AsyncTask para hacer las oepraciones con la base de datos(para las consultas a la API no tiene que ser ASYNC)
 //Los metodos los llama el presenter y distinguiremos dos casos tener la info en la BD y no
 //Llamamos al metodo que saque la info de la BD si la lista que devuelve esta vacia llamaremos a otro metodo que saque la info de la API ,cuando la info este en el mdoelo la meteremos en la baseDeDatos y luego delvolveremos la lista al presenter
 //Si la info esta en la base de datos se la devolvemos al Presenter


 public void ligasBaseDeDatos(Response.Listener<List<League>> listener) {
  new extraerLigasDeLaBaseDeDatos(listener).execute();//Creamos un nuevo Thread y execute lo lanza
 }

 public void equiposBaseDeDatos(Response.Listener<List<Team>> listener, int id) {
  new extraerEquiposDeLaBaseDeDatos(listener, id).execute();//Creamos un nuevo Thread y execute lo lanza
 }

//DUDA --> la informacion de la clasificacion se obtiene siempre de una llamada a la API por tanto no hay que crear una clase que extienda de async task solo llamar a  la funcion de la clase de las queris


 private static class extraerLigasDeLaBaseDeDatos extends AsyncTask<Void, Void, List<League>> {//Dentro de una clase puede haber otra privada

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


 private static class extraerEquiposDeLaBaseDeDatos extends AsyncTask<Integer, Void, List<Team>> {//Dentro de una clase puede haber otra privada

  private Response.Listener<List<Team>> listener;

  private extraerEquiposDeLaBaseDeDatos(Response.Listener<List<Team>> listener, int id) {
   this.listener = listener;
  }

  @Override
  protected List<Team> doInBackground(Integer... id) {
   return dao.infoEquipos(id[0]);
  }

  @Override
  protected void onPostExecute(List<Team> teams) {
   listener.onResponse(teams);
  }

 }

 public void ligasAPI(Response.Listener<List<League>> listener){


  Response.ErrorListener errorListener = new Response.ErrorListener() {
   @Override
   public void onErrorResponse(VolleyError error) {

   }
  };


   dataAccess.getLeagues(leaguesToFollow,listener,errorListener);

   }

 }