package com.example.mateusz.fantasy.team.model;

import java.util.ArrayList;

public class Player {

    //Player positions
    public static final String GOALKEEPER = "gk";
    public static final String DEFENDER = "def";
    public static final String MIDFIELDER = "mid";
    public static final String ATTACKER = "atk";

    private String name;
    private String team;
    private String position;
    private int points;
    private int sub;
    private int captain;

    public Player(String name, String team, String position, int points, int sub, int captain) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.points = points;
        this.sub = sub;
        this.captain = captain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getCaptain() {
        return captain;
    }

    public void setCaptain(int captain) {
        this.captain = captain;
    }

    public static ArrayList<Player> getMockPlayerData(){
        ArrayList<Player> players = new ArrayList<Player>();

        players.add(new Player("De Gea", Teams.MAN_UTD, GOALKEEPER, 6,0,0));
        players.add(new Player("Azpilicueta", Teams.CHELSEA, DEFENDER,8,0,0));
        players.add(new Player("Valencia", Teams.MAN_UTD, DEFENDER,2,0,0));
        players.add(new Player("Vertronghen", Teams.SPURS, DEFENDER,1,0,0));
        players.add(new Player("Daniels", Teams.BOURNEMOUTH, DEFENDER,8,0,0));
        players.add(new Player("Sane", Teams.MAN_CITY, MIDFIELDER,7,0,0));
        players.add(new Player("McArthur", Teams.CRYSTAL_PALACE, MIDFIELDER,8,0,0));
        players.add(new Player("Salah", Teams.LIVERPOOL_FC, MIDFIELDER,16,0,0));
        players.add(new Player("Doucure", Teams.WATFORD, MIDFIELDER,3,0,0));
        players.add(new Player("Jesus", Teams.MAN_CITY, ATTACKER,8,0,0));
        players.add(new Player("Lukaku", Teams.MAN_UTD, ATTACKER,12,0,0));

        return players;
    }
}
