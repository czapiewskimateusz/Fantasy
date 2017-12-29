package com.example.mateusz.fantasy.team.model.repo;

import java.util.ArrayList;

public class Teams {

    public static final String ARSENAL = "Arsenal";
    public static final String BOURNEMOUTH = "Bournemouth";
    public static final String BRIGHTON = "Brighton";
    public static final String BURNLEY = "Burnley";
    public static final String CHELSEA = "Chelsea";
    public static final String CRYSTAL_PALACE = "Crystal Palace";
    public static final String EVERTON = "Everton";
    public static final String HUDDERSFIELD = "Huddersfield";
    public static final String LEICESTER = "Leicester";
    public static final String LIVERPOOL_FC = "Liverpool FC";
    public static final String MAN_CITY = "Man City";
    public static final String MAN_UTD = "Man Utd";
    public static final String NEWCASTLE = "Newcastle";
    public static final String SOUTHAMPTON = "Southampton";
    public static final String SPURS = "Spurs";
    public static final String STOKE = "Stoke";
    public static final String SWANSEA= "Swansea";
    public static final String WATFORD = "Watford";
    public static final String WEST_BROM = "West Brom";
    public static final String WEST_HAM = "West Ham";

    public static ArrayList<String> getListOfAllTeams(){
        ArrayList<String> teams = new ArrayList<>();
        teams.add(ARSENAL);
        teams.add(BOURNEMOUTH);
        teams.add(BRIGHTON);
        teams.add(BURNLEY);
        teams.add(CHELSEA);
        teams.add(CRYSTAL_PALACE);
        teams.add(EVERTON);
        teams.add(HUDDERSFIELD);
        teams.add(LEICESTER);
        teams.add(LIVERPOOL_FC);
        teams.add(MAN_CITY);
        teams.add(MAN_UTD);
        teams.add(NEWCASTLE);
        teams.add(SOUTHAMPTON);
        teams.add(SPURS);
        teams.add(STOKE);
        teams.add(SWANSEA);
        teams.add(WATFORD);
        teams.add(WEST_BROM);
        teams.add(WEST_HAM);
        return teams;
    }

}
