package com.example.mateusz.fantasy.leagues.model.repo;


public class League {

    public static final String LEAGUE_ID = "LEAGUE_ID";
    public static final String NAME = "name";
    public static final String USER_POSITION = "rank";
    public static final String CODE = "code";
    public static final String NUMBER_OF_PLAYERS = "numberOfPlayers";

    private int leagueId;
    private String name;
    private int rank;
    private String code;
    private int numberOfPlayers;

    public League(String name, int rank, String code, int leagueId, int numberOfPlayers) {

        this.name = name;
        this.rank = rank;
        this.code = code;
        this.leagueId = leagueId;
        this.numberOfPlayers = numberOfPlayers;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

}
