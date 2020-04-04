package com.example.ujisoccer.ExtraActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujisoccer.MainActivity.MainActivity;
import com.example.ujisoccer.MainActivity.Presenter;
import com.example.ujisoccer.Model;
import com.example.ujisoccer.R;
import com.example.ujisoccer.Standing.Player;
import com.example.ujisoccer.Standing.StandingsActivity;

import java.util.List;

public class ExtraActivity extends AppCompatActivity{
    private int idLiga;
    private int idEquipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);
        Intent intent = getIntent();
        idEquipo = intent.getIntExtra("equipoId", 0);
        idLiga = intent.getIntExtra("ligaId", 0);
        Model model = Model.getInstance(this);
        final ExtraPresenter presenter = new ExtraPresenter(this,model);

        Log.d("myTag","ID LIGA =" +String.valueOf(idLiga));
        Log.d("myTag", "ID EQUIPO ="+String.valueOf(idEquipo));

    }

    public int getIdLiga() {
        return idLiga;
    }

    public int getIdEquipo() {
        return idEquipo;
    }


    public void llenarListView(final List<Player> lista) {
        ExtraAdapter customAdapter = new ExtraAdapter(this,lista);
        View footerView = getLayoutInflater().inflate(R.layout.header_extra, null);


        ListView list = findViewById(R.id.goleadores);
        list.addHeaderView(footerView,"Header",false);//Add view to list view as footer view
        list.setAdapter(customAdapter);

    }

    public void aviso() {
        TextView aviso = findViewById(R.id.aviso);
        aviso.setVisibility(View.VISIBLE);
    }
}
