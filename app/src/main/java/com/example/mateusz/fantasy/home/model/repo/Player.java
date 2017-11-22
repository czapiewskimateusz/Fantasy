package com.example.mateusz.fantasy.home.model.repo;

import java.util.ArrayList;

import static com.example.mateusz.fantasy.home.model.repo.Teams.BOURNEMOUTH;
import static com.example.mateusz.fantasy.home.model.repo.Teams.CHELSEA;
import static com.example.mateusz.fantasy.home.model.repo.Teams.CRYSTAL_PALACE;
import static com.example.mateusz.fantasy.home.model.repo.Teams.LIVERPOOL_FC;
import static com.example.mateusz.fantasy.home.model.repo.Teams.MAN_CITY;
import static com.example.mateusz.fantasy.home.model.repo.Teams.MAN_UTD;
import static com.example.mateusz.fantasy.home.model.repo.Teams.SPURS;
import static com.example.mateusz.fantasy.home.model.repo.Teams.WATFORD;

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

        players.add(new Player("De Gea",MAN_UTD, GOALKEEPER, 6,0,0));
        players.add(new Player("Azpilicueta",CHELSEA, DEFENDER,8,0,0));
        players.add(new Player("Valencia",MAN_UTD, DEFENDER,2,0,0));
        players.add(new Player("Vertronghen",SPURS, DEFENDER,1,0,0));
        players.add(new Player("Daniels",BOURNEMOUTH, DEFENDER,8,0,0));
        players.add(new Player("Sane",MAN_CITY, MIDFIELDER,7,0,0));
        players.add(new Player("McArthur",CRYSTAL_PALACE, MIDFIELDER,8,0,0));
        players.add(new Player("Salah",LIVERPOOL_FC, MIDFIELDER,16,0,0));
        players.add(new Player("Doucure",WATFORD, MIDFIELDER,3,0,0));
        players.add(new Player("Jesus",MAN_CITY, ATTACKER,8,0,0));
        players.add(new Player("Lukaku",MAN_UTD, ATTACKER,12,0,0));

        return players;
    }
}
