package com.example.ujisoccer.Standing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ujisoccer.Database.TeamInStanding;
import com.example.ujisoccer.R;

import java.util.List;

public class Adapter extends BaseAdapter {

    private Context context;
    private List<TeamInStanding> teams;

    public Adapter (Context context,  List<TeamInStanding> teams) {

        this.context = context;
        this.teams = teams;
    }

    @Override
    public int getCount() {
        return teams.size();
    }

    @Override
    public TeamInStanding getItem(int position) {
        return teams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) view = convertView;
        else view = LayoutInflater.from(context).inflate(R.layout.customlayout, parent, false);

        TextView  posicion = view.findViewById(R.id.pos);
        posicion.setText(String.valueOf(getItem(position).position));

        TextView  equipo = view.findViewById(R.id.team);
        equipo.setText(getItem(position).name);

        TextView  pl = view.findViewById(R.id.pl);
        pl.setText(String.valueOf(getItem(position).playedGames));

        TextView  w = view.findViewById(R.id.w);
        w.setText(String.valueOf(getItem(position).won));

        TextView  d = view.findViewById(R.id.d);
        d.setText(String.valueOf(getItem(position).draw));

        TextView  l = view.findViewById(R.id.l);
        l.setText(String.valueOf(getItem(position).lost));

        TextView  pt = view.findViewById(R.id.pt);
        pt.setText(String.valueOf(getItem(position).points));

        TextView  gf = view.findViewById(R.id.gf);
        gf.setText(String.valueOf(getItem(position).goalsFor));

        TextView  ga = view.findViewById(R.id.ga);
        ga.setText(String.valueOf(getItem(position).goalsAgainst));

        return view;
    }
}
