package com.example.ujisoccer.ExtraActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.ujisoccer.R;
import com.example.ujisoccer.Standing.Player;

import java.util.List;

public class ExtraAdapter extends BaseAdapter {

    private Context context;
    private List<Player> jugadores;

    public ExtraAdapter (ExtraActivity context, List<Player> jugadores) {

        this.context = context;
        this.jugadores = jugadores;
    }

    @Override
    public int getCount() {
        return jugadores.size();
    }

    @Override
    public Player getItem(int position) {
        return jugadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) view = convertView;
        else view = LayoutInflater.from(context).inflate(R.layout.custom_layout_extra, parent, false);

        TextView  nombre = view.findViewById(R.id.name);
        nombre.setText(getItem(position).name);

        TextView  posicion = view.findViewById(R.id.pos);
        posicion.setText(getItem(position).position);

        TextView  birth = view.findViewById(R.id.birth);
        birth.setText(getItem(position).dateBirth);

        TextView  pais = view.findViewById(R.id.country);
        pais.setText(getItem(position).nationality);

        TextView  goles = view.findViewById(R.id.goals);
        goles.setText(String.valueOf(getItem(position).goals));



        return view;
    }
}
