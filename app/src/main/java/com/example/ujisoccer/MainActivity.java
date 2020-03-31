package com.example.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.ujisoccer.Database.League;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Inter {

    private Spinner spinner;
    private TextView textoPais;
    private TextView infoPais;
    private TextView textoInicio;
    private TextView infoInicio;
    private TextView textoFin;
    private TextView infoFin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoPais = findViewById(R.id.pais);
        Model model = Model.getInstance(this);
        Presenter presenter = new Presenter(this,model);
    }


    public void showLeagues() {

    }



    @Override
    public void mostrarInfoLiga(List<League> leagues) {
       infoPais.setText("1234");
    }
}


