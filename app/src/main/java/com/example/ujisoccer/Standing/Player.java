package com.example.ujisoccer.Standing;

public class Player {
    public String name;
    public String position;
    public String dateBirth;
    public String nationality;
    public int goals;

    public Player(String name, String position, String dateBirth, String nationality, int goals) {
        this.name = name;
        this.position = position;
        this.dateBirth = dateBirth;
        this.nationality = nationality;
        this.goals = goals;
    }
}
