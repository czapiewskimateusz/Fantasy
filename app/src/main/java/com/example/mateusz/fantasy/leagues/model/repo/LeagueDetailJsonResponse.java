package com.example.mateusz.fantasy.leagues.model.repo;

public class LeagueDetailJsonResponse {

    private UserRank[] usersRank;

    public UserRank[] getUsersRank() {
        return usersRank;
    }

    public void setUsersRank(UserRank[] usersRank) {
        this.usersRank = usersRank;
    }
}
