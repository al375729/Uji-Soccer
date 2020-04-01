package com.example.ujisoccer;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.ujisoccer.Database.League;
import com.example.ujisoccer.Database.Team;
import com.example.ujisoccer.Database.TeamInStanding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FootballDataAccess {
    private static final String token = "X-Auth-Token";
    private static final String miClave = "d24a05e231994ca18ad7a0512e1d4c9d";

    private static final String urlBase = "https://api.football-data.org/v2/";
    private static final String urlCompeticiones = urlBase + "competitions?plan=TIER_ONE";


    private static final String listaDeCompeticiones = "competitions";
    private static final String idCompeticion = "id";
    private static final String nombreCompeticion = "name";
    private static final String areaDeCompeticion = "area";
    private static final String nombreArea = "name";
    private static final String temporada = "currentSeason";
    private static final String inicioTemporada = "startDate";
    private static final String finTemporada = "endDate";

    private static final String clasificacion = "standings";
    private static final String tipoClasificacion = "type";
    private static final String valorTipo = "TOTAL";
    private static final String tabla = "table";
    private static final String posicion = "position";
    private static final String idEquipo = "id";
    private static final String nombreEquipo = "name";
    private static final String partidosJugados = "playedGames";
    private static final String partidosGanados = "won";
    private static final String partidosEmpatados = "draw";
    private static final String partidosPerdidos = "lost";
    private static final String puntos = "points";
    private static final String golesFavor = "goalsFor";
    private static final String golesContra = "goalsAgainst";

    private static final String listaEqupios = "teams";
    private static final String nombreCortoEquipo = "shortName";
    private static final String web = "website";
    private static final String colores = "clubColors";
    private static final String fundado = "founded";
    private static final String venue = "venue";







    private RequestQueue requestQueue;

    public FootballDataAccess(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    private void sendRequest(String url, Response.Listener<JSONObject> listener , Response.ErrorListener errorListener){//Funcion a la que llamaremos para construir y mandar las peticiones
        Log.d("myTag", "sendrequest");
        JsonRequest request = new JsonObjectRequest(Request.Method.GET, url,null,listener,errorListener ){
            @Override
            public Map<String,String> getHeaders(){
                Map<String,String> headers = new HashMap<>();
                headers.put(token,miClave);
                return headers;
            }

        };
        requestQueue.add(request);
    }

    public void getLeagues(final Set<Integer> leagues, final Response.Listener<List<League>> listener, final Response.ErrorListener errorListener ){//Funcion que le pasa los argumentos a SendRequest para obtener una lista de ligas de la API
        Log.d("myTag", "He llegado a getleagues");
        sendRequest(urlCompeticiones, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<League> leaguesList = parseLeagues(response, leagues);
                listener.onResponse(leaguesList);
            }
        }, errorListener);

    }

    private List<League> parseLeagues(JSONObject response, Set<Integer> leagues) {//Funcion que se le pasa la respuesta de la API al pedirle las ligas y separa la informacion y crea un instancia de la clase Liga y las añade a la lista que devolverá la funcion
        Log.d("myTag", "parseLeagues");
        List<League> lista = new ArrayList<>();

        try{
            JSONArray competiciones = response.getJSONArray(listaDeCompeticiones);

            for(int i = 0; i < competiciones.length();i++){
                JSONObject competicion = competiciones.getJSONObject(i);
                int id = competicion.getInt(idCompeticion);
                Log.d("myTag", "comprobacion "+ id);
                if (leagues.contains(id)){

                    String nombre = competicion.getString(nombreCompeticion);
                    JSONObject area = competicion.getJSONObject(areaDeCompeticion);
                    String pais = area.getString(nombreArea);
                    JSONObject season = competicion.getJSONObject("currentSeason");
                    String inicio = season.getString(inicioTemporada);
                    String fin = season.getString(finTemporada);
                    lista.add(new League(id,nombre,pais,inicio,fin));
                    Log.d("myTag", "Insertado");
                }
            }
        } catch (JSONException e) {
            return null;
        }
        return lista;
    }

    public void getTeams(final int id, final Response.Listener<List<Team>> listener, final Response.ErrorListener errorListener ){//
        String url= urlBase + "competitions/%s/teams,id";
        sendRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Team> equipo = parseTeams(response , id);
                listener.onResponse(equipo);
            }
        }, errorListener);

    }

    private List<Team> parseTeams(JSONObject response,int ligaId) {
        List<Team> lista = new ArrayList<>();
        try{
            JSONArray equipos = response.getJSONArray(listaEqupios);
            for (int i = 0; i < equipos.length();i++){
                JSONObject equipo = equipos.getJSONObject(i);
                int id = equipo.getInt(idEquipo);
                String name =equipo.getString(nombreEquipo);
                String shortName =equipo.getString(nombreCortoEquipo);
                String website =equipo.getString(web);
                String founded =equipo.getString(fundado);
                String clubColours =equipo.getString(colores);
                String venues =equipo.getString(venue);
                lista.add(new Team(id,name,shortName,clubColours,website,ligaId,founded,venues));
            }

        } catch (JSONException e){
            return null;
        }
        return lista;
    }

    public void getStandings(final int id, final Response.Listener<List<TeamInStanding>> listener, final Response.ErrorListener errorListener ){//
        String url= urlBase + "competitions/%s/standings,id";
        sendRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<TeamInStanding> equipo = parseStandings(response , id);
                listener.onResponse(equipo);
            }
        }, errorListener);

    }

    private List<TeamInStanding> parseStandings(JSONObject response, int ligaId) {
        List<TeamInStanding> lista = new ArrayList<>();
        try{
            JSONArray equipos = response.getJSONArray(clasificacion);
            for (int i = 0; i < equipos.length();i++){
                JSONObject equipo = equipos.getJSONObject(i);
                String type = equipo.getString(tipoClasificacion);
                if(type == valorTipo);{

                    int id = equipo.getInt(idEquipo);
                    String name =equipo.getString(nombreEquipo);
                    int position =equipo.getInt(posicion);
                    int playedGames =equipo.getInt(partidosJugados);
                    int won =equipo.getInt(partidosGanados);
                    int draw =equipo.getInt(partidosEmpatados);
                    int lost =equipo.getInt(partidosPerdidos);
                    int points =equipo.getInt(puntos);
                    int goalsFor =equipo.getInt(golesFavor);
                    int goalsAgainst =equipo.getInt(golesContra);
                    lista.add(new TeamInStanding(id,name,position,playedGames,won,draw,lost,points,goalsFor,goalsAgainst));
                }
            }

        } catch (JSONException e){
            return null;
        }
        return lista;

    }


}

