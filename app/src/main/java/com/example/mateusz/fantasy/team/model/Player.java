package com.example.mateusz.fantasy.team.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

public class Player implements Comparable<Player>{

    //Player positions
    public static final String GOALKEEPER = "GK"; // 1
    public static final String DEFENDER = "DEF"; // 2
    public static final String MIDFIELDER = "MID"; // 3
    public static final String ATTACKER = "ATK"; // 4

    private int playerId;
    private String name;
    private int position;
    private String team;
    private double value;
    private int totalPoints;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Player(int playerId, String name, String position, String team, double value, int totalPoints) {
        this.playerId = playerId;
        this.name = name;
        this.position = setPosition(position);
        this.team = team;
        this.value = value;
        this.totalPoints = totalPoints;
    }

    public static int setPosition(String position){
        if (position.equals(GOALKEEPER)) return 1;
        if (position.equals(DEFENDER)) return 2;
        if (position.equals(MIDFIELDER)) return 3;
        if (position.equals(ATTACKER)) return 4;
        return 0;
    }

    public static String getPosition(int position){
        if (position==1) return GOALKEEPER;
        if (position==2) return DEFENDER;
        if (position==3) return MIDFIELDER;
        if (position==4) return ATTACKER;
        return "ERROR";
    }

    @Override
    public int compareTo(@NonNull Player player) {
        return position-player.getPosition();
    }

    public static ArrayList<Player> getMockPlayerData(){
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player(11,"Lukaku",ATTACKER, Teams.MAN_UTD,10.8,63));
        players.add(new Player(2,"Azpilicueta", DEFENDER,Teams.CHELSEA,6.7,67));
        players.add(new Player(3,"Valencia", DEFENDER,Teams.MAN_UTD ,6.8,78));
        players.add(new Player(8,"Salah",MIDFIELDER, Teams.LIVERPOOL_FC ,9.8,81));
        players.add(new Player(4,"Vertronghen", DEFENDER,Teams.SPURS,5.6,67));
        players.add(new Player(1,"De Gea", GOALKEEPER,Teams.MAN_UTD, 6.1,76));
        players.add(new Player(6,"Sane", MIDFIELDER, Teams.MAN_CITY ,8.2,85));
        players.add(new Player(7,"McArthur",MIDFIELDER, Teams.CRYSTAL_PALACE ,4.4,65));
        players.add(new Player(9,"Doucure",MIDFIELDER, Teams.WATFORD,6.2,62));
        players.add(new Player(10,"Jesus", ATTACKER,Teams.MAN_CITY,8.7,69));
        players.add(new Player(5,"Daniels", DEFENDER,Teams.BOURNEMOUTH, 4.7,25));
        Collections.sort(players);
        return players;
    }

    public static ArrayList<Player> getMockToTransferData(){
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player(11,"Lukaku",ATTACKER, Teams.MAN_UTD,10.8,63));
        players.add(new Player(10,"Jesus", ATTACKER,Teams.MAN_CITY,8.7,69));
        players.add(new Player(5,"Daniels", DEFENDER,Teams.BOURNEMOUTH, 4.7,25));
        Collections.sort(players);
        return players;
    }
}
