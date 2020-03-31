package com.example.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.ujisoccer.Database.League;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spiner = findViewById(R.id.spinner);
    TextView infoPais = findViewById(R.id.pais);
    TextView infoInicio = findViewById(R.id.inicio);
    TextView infoFin = findViewById(R.id.fin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Presenter presenter = new Presenter(this,this);
        showLeagues();
    }


    public void showLeagues() {


        Response.Listener listener = new Response.Listener<List<League>>() {
            @Override
            public void onResponse(List<League> response) {
            //rellenar el spinner con la lista devuelta
            }
        };
        Presenter.obtenerDatosLiga(listener);
    }

    public void showLeagueInfo (League liga){
        infoPais.setText(liga.country);
        infoInicio.setText(liga.start);
        infoFin.setText(liga.end);
    }

}


