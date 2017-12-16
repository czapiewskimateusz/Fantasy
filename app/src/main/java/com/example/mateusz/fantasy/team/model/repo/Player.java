package com.example.mateusz.fantasy.team.model.repo;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Player implements Comparable<Player>, Serializable{

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
    private int currentGWPoints;

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

    public int getCurrentGWPoints() {
        return currentGWPoints;
    }

    public void setCurrentGWPoints(int currentGWPoints) {
        this.currentGWPoints = currentGWPoints;
    }

    public Player(int playerId, String name, String position, String team, double value, int totalPoints, int currentGWPoints) {
        this.playerId = playerId;
        this.name = name;
        this.position = setPosition(position);
        this.team = team;
        this.value = value;
        this.totalPoints = totalPoints;
        this.currentGWPoints = currentGWPoints;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof  Player)) return false;
        return getPlayerId()==((Player) obj).getPlayerId();
    }

    public static ArrayList<Player> getMockPlayerData(){
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player(11,"Lukaku",ATTACKER, Teams.MAN_UTD,10.8,63, 8));
        players.add(new Player(2,"Azpilicueta", DEFENDER,Teams.CHELSEA,6.7,67, 6));
        players.add(new Player(3,"Valencia", DEFENDER,Teams.MAN_UTD ,6.8,78, 6));
        players.add(new Player(8,"Salah",MIDFIELDER, Teams.LIVERPOOL_FC ,9.8,81, 1));
        players.add(new Player(4,"Vertronghen", DEFENDER,Teams.SPURS,5.6,67, 4));
        players.add(new Player(1,"De Gea", GOALKEEPER,Teams.MAN_UTD, 6.1,76, 5));
        players.add(new Player(6,"Sane", MIDFIELDER, Teams.MAN_CITY ,8.2,85, 12));
        players.add(new Player(7,"McArthur",MIDFIELDER, Teams.CRYSTAL_PALACE ,4.4,65, 6));
        players.add(new Player(9,"Doucure",MIDFIELDER, Teams.WATFORD,6.2,62, 2));
        players.add(new Player(10,"Jesus", ATTACKER,Teams.MAN_CITY,8.7,69, 8));
        players.add(new Player(5,"Daniels", DEFENDER,Teams.BOURNEMOUTH, 4.7,25, 6));
        Collections.sort(players);
        return players;
    }

    public static ArrayList<Player> getMockTransferData(){
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player(11,"Lukaku",ATTACKER, Teams.MAN_UTD,10.8,63, 5));
        players.add(new Player(12,"Solanke",ATTACKER, Teams.LIVERPOOL_FC,4.7,13, 4));
        players.add(new Player(16,"Kane",ATTACKER, Teams.SPURS,12.8,97, 2));
        players.add(new Player(13,"Oxlade-Chamberlain",MIDFIELDER, Teams.LIVERPOOL_FC,6.5,37, 7));
        players.add(new Player(14,"Matip",DEFENDER, Teams.LIVERPOOL_FC,5.1,48, 2));
        players.add(new Player(2,"Azpilicueta", DEFENDER,Teams.CHELSEA,6.7,67, 8));
        players.add(new Player(17,"Otamendi", DEFENDER,Teams.MAN_CITY,6.8,72, 6));
        players.add(new Player(3,"Valencia", DEFENDER,Teams.MAN_UTD ,6.8,78, 3));
        players.add(new Player(8,"Salah",MIDFIELDER, Teams.LIVERPOOL_FC ,9.8,101, 1));
        players.add(new Player(4,"Vertronghen", DEFENDER,Teams.SPURS,5.6,67, 3));
        players.add(new Player(1,"De Gea", GOALKEEPER,Teams.MAN_UTD, 6.1,76, 6));
        players.add(new Player(15,"Cech", GOALKEEPER,Teams.ARSENAL, 5.7,51, 2));
        players.add(new Player(6,"Sane", MIDFIELDER, Teams.MAN_CITY ,8.2,85, 3));
        players.add(new Player(7,"McArthur",MIDFIELDER, Teams.CRYSTAL_PALACE ,4.4,65, 7));
        players.add(new Player(9,"Doucure",MIDFIELDER, Teams.WATFORD,6.2,62, 8));
        players.add(new Player(10,"Jesus", ATTACKER,Teams.MAN_CITY,8.7,69, 4));
        players.add(new Player(5,"Daniels", DEFENDER,Teams.BOURNEMOUTH, 4.7,25, 2));
        Collections.sort(players);
        return players;
    }
}
