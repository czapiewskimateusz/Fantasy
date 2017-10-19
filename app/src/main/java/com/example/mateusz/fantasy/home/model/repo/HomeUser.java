package com.example.mateusz.fantasy.home.model.repo;

public class HomeUser {

    private String firstName;
    private String lastName;
    private String teamName;

    public HomeUser(String firstName, String lastName, String teamName) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.teamName = teamName;

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
}
