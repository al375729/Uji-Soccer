package com.example.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ujisoccer.Database.League;
import com.example.ujisoccer.Standing.StandingsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Inter {

    private Spinner spinner;
    private TextView textoPais;
    private TextView infoPais;
    private TextView textoInicio;
    private TextView infoInicio;
    private TextView textoFin;
    private TextView infoFin;
    private Button boton;
    public League liga;
    public int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        infoPais = findViewById(R.id.pais);
        infoFin =  findViewById(R.id.fin);
        infoInicio = findViewById(R.id.inicio);
        Model model = Model.getInstance(this);
        final Presenter presenter = new Presenter(this,model);
        boton = findViewById(R.id.button);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this, StandingsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }



    @Override
    public void mostrarInfoLiga(List<League> leagues) {
        ArrayAdapter<League> adapter = new ArrayAdapter<League>(this,android.R.layout.simple_spinner_item,leagues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                liga = (League) parent.getSelectedItem();
                ligaSeleccionada(liga);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            //hideText();
            }
        });





    }


    @Override
    public void startStandings() {

    }

    public void ligaSeleccionada(League liga){

        infoPais.setText(liga.country);
        infoFin.setText(liga.end);
        infoInicio.setText(liga.start);
        id =liga.id;
    }

}


