package com.example.mateusz.fantasy.Home.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 18.09.2017.
 */

public class League {
    private int league_id;
    private String name;
    private int userPosition;
    private String code;

    public League(String name, int userPosition, String code, int league_id) {
        this.name = name;
        this.userPosition = userPosition;
        this.code = code;
        this.league_id = league_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(int userPosition) {
        this.userPosition = userPosition;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLeague_id() {
        return league_id;
    }

    public void setLeague_id(int league_id) {
        this.league_id = league_id;
    }

    public static List<League> initializeData() {
        List<League> leagues = new ArrayList<>();
        leagues.add(new League("Liga Niezwykłych Gentelmanów", 41, "441116",41124));
        leagues.add(new League("Blok Ekipa", 12, "415516",1242145));
        leagues.add(new League("Elyta", 1, "412516",5242));
        leagues.add(new League("Mordeczki", 125, "215516",1234));
        leagues.add(new League("Fantazyjniacy", 125, "561216",12125));
        leagues.add(new League("EPL FTW", 2661, "098216",1235));
        leagues.add(new League("Partia Piłkarzy", 2151, "918273",125125));
        leagues.add(new League("Chorzy Amatorzy", 211, "182745",65465));
        leagues.add(new League("Liga Globalna", 3, "124716",45654));
        leagues.add(new League("Liga kibiców Liverpool FC", 111, "151251",634636));
        return leagues;
    }
}
