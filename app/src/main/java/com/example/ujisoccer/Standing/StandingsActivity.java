package com.example.ujisoccer.Standing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ujisoccer.Database.Team;
import com.example.ujisoccer.Database.TeamInStanding;
import com.example.ujisoccer.ExtraActivity.ExtraActivity;
import com.example.ujisoccer.MainActivity.MainActivity;
import com.example.ujisoccer.Model;
import com.example.ujisoccer.R;

import java.util.List;

public class StandingsActivity extends AppCompatActivity implements InterfaceStanding {
    private int idLiga;
    private List<Team> equipos;
    public void setEquipos(List<Team> equipos) {
        this.equipos = equipos;
    }

    //ListView list = findViewById(R.id.listView);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        Intent intent = getIntent();
        idLiga = intent.getIntExtra("id", 0);
        Log.d("myTag", String.valueOf(idLiga));

        Model model = Model.getInstance(this);
        final PresenterStanding presenter = new PresenterStanding(this,model);
    }

    public int getIdLiga() {
        return idLiga;
    }

    @Override
    public void llenarListView(final List<TeamInStanding> lista) {
        Adapter customAdapter = new Adapter(this,lista);
        View footerView = getLayoutInflater().inflate(R.layout.header, null);


        ListView list = findViewById(R.id.listView);
        list.addHeaderView(footerView,"Header",false);//Add view to list view as footer view
        list.setAdapter(customAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("myTag", lista.get(position-1).name);
               openDialog(position-1,lista);

            }
        });
    }

    private void openDialog(int id,List<TeamInStanding> lista) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(StandingsActivity.this);
        View dialog = getLayoutInflater().inflate(R.layout.dialog,null);
        mBuilder.setView(dialog);
        final AlertDialog dialogo =mBuilder.create();
        dialogo.show();
        Team equipo = null;
        for(int i =0;i<equipos.size();i++){
            if(equipos.get(i).id == lista.get(id).id) {
                equipo = equipos.get(i);
                Log.d("myTag", equipo.name);
            }
        }

        final Team finalEquipo = equipo;
        TextView name = dialog.findViewById(R.id.name);
        name.setText(equipo.name);

        TextView shortname = dialog.findViewById(R.id.shortnameDisplay);
        shortname.setText(equipo.shortname);

        TextView founded = dialog.findViewById(R.id.foundedDisplay);
        founded.setText(equipo.founded);

        TextView stadium = dialog.findViewById(R.id.stadiumDisplay);
        stadium.setText(equipo.venue);

        TextView colours = dialog.findViewById(R.id.textView22);
        colours.setText(equipo.venue);

        TextView exit = dialog.findViewById(R.id.okay);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.cancel();
            }
        });

        TextView ejercicio =dialog.findViewById(R.id.ejer);

        ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarEjercicio(finalEquipo.id);
            }
        });

        TextView web = dialog.findViewById(R.id.web);

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarWeb(finalEquipo.web);
            }
        });
    }

    private void llamarEjercicio(int equipoId) {
        Intent intent= new Intent(StandingsActivity.this, ExtraActivity.class);
        intent.putExtra("equipoId",equipoId);
        intent.putExtra("ligaId",idLiga);
        startActivity(intent);
    }

    private void llamarWeb(String web) {

            try {
                Uri webpage = Uri.parse(web);
                Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(myIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No WEB data",  Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

    }


}
