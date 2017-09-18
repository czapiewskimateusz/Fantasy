package com.example.mateusz.fantasy.Home.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 18.09.2017.
 */

public class League {
    private String name;
    private int userPosition;
    private String code;

    public League(String name, int userPosition, String code) {
        this.name = name;
        this.userPosition = userPosition;
        this.code = code;
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



    public static List<League> initializeData(){
        List<League> leagues = new ArrayList<>();
        leagues.add(new League("LIGA NIEZWYKLYCH GENTELMANOW", 41, "441116"));
        leagues.add(new League("BLOK EKIPA ", 12, "415516"));
        leagues.add(new League("ELYTA", 1, "412516"));
        leagues.add(new League("ZIOMECZKI", 125, "215516"));
        leagues.add(new League("MISTRZOWIE FANTASY", 125, "561216"));
        leagues.add(new League("READY4S", 2661, "098216"));
        leagues.add(new League("PARTIA PILKARZY", 2151, "918273"));
        leagues.add(new League("CHORZY AMATORZY", 211, "182745"));
        leagues.add(new League("LIGA GLOBALNA", 3, "124716"));
        leagues.add(new League("LIGA LFC", 111, "151251"));
        return leagues;
    }
}
