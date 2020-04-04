package com.example.ujisoccer.MainActivity;

import com.example.ujisoccer.Database.League;

import java.util.List;

public interface Inter {
    void mostrarInfoLiga(List<League> leagues);

    void startStandings();

    void showText();

    void hideProgressBar();

    void showError(String cannot_connect_the_internet);
}
