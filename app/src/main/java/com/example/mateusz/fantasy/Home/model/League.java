package com.example.mateusz.fantasy.Home.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 18.09.2017.
 */

public class League {

    public static final String LEAGUE_ID = "LEAGUE_ID";
    public static final String NAME = "name";
    public static final String USER_POSITION = "userPosition";
    public static final String CODE = "code";
    public static final String NUMBER_OF_PLAYERS = "numberOfPlayers";

    private int league_id;
    private String name;
    private int userPosition;
    private String code;
    private int numberOfPlayers;



    public League(String name, int userPosition, String code, int league_id,int numberOfPlayers) {

        this.name = name;
        this.userPosition = userPosition;
        this.code = code;
        this.league_id = league_id;
        this.numberOfPlayers = numberOfPlayers;

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

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public static List<League> initializeData() {
        List<League> leagues = new ArrayList<>();
        leagues.add(new League("Liga Niezwykłych Gentelmanów", 41, "441116",41124,1234516));
        leagues.add(new League("Blok Ekipa", 12, "415516",1242145,2154125));
        leagues.add(new League("Elyta", 1, "412516",5242,23523));
        leagues.add(new League("Mordeczki", 125, "215516",1234,325232));
        leagues.add(new League("Fantazyjniacy", 125, "561216",12125,876645));
        leagues.add(new League("EPL FTW", 2661, "098216",1235,12351));
        leagues.add(new League("Partia Piłkarzy", 2151, "918273",125125,363232));
        leagues.add(new League("Chorzy Amatorzy", 211, "182745",65465,644745));
        leagues.add(new League("Liga Globalna", 3, "124716",45654,124151));
        leagues.add(new League("Liga kibiców Liverpool FC", 111, "151251",634636,121561));
        return leagues;
    }
}
