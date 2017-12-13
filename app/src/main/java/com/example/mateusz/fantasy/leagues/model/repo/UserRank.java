package com.example.mateusz.fantasy.leagues.model.repo;


public class UserRank {

    private int rank;
    private int userId;
    private String firstName;
    private String lastName;
    private String teamName;
    private int totalPoints;

    public UserRank(int rank, int userId, String firstName, String lastName, String teamName, int totalPoints) {
        this.rank = rank;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamName = teamName;
        this.totalPoints = totalPoints;
        this.userId = userId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
