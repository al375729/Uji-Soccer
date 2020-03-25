package com.example.ujisoccer.Database;

public class TeamInStanding {

    public int id;
    public  String name;
    public int position;
    public int playedGames;
    public int won;
    public int draw;
    public int lost;
    public int points;
    public int goalsFor;
    public int goalsAgainst;


    public TeamInStanding(int id,String name,int position, int playedGames, int won,int draw, int lost, int points, int goalsFor,int goalsAgainst){
        this.id=id;
        this.name=name;
        this.position=position;
        this.playedGames=playedGames;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.points = points;
        this.goalsFor=goalsFor;
        this.goalsAgainst=goalsAgainst;

    }
}
