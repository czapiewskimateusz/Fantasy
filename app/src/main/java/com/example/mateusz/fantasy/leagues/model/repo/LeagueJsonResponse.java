package com.example.mateusz.fantasy.leagues.model.repo;

public class LeagueJsonResponse {

    private League[] leagues;

    public void setLeagues(League[] leagues) {
        this.leagues = leagues;
    }

    public League[] getLeagues() {
        return leagues;
    }
}
