package com.example.mateusz.fantasy.Home.model;


import java.util.ArrayList;
import java.util.List;

public class UserRank {

    private int rank;
    private String firstName;
    private String lastName;
    private String teamName;
    private int totalPoints;

    public UserRank(int rank, String firstName, String lastName, String teamName, int totalPoints) {
        this.rank = rank;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamName = teamName;
        this.totalPoints = totalPoints;
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

    public static List<UserRank> initializeData() {

        List<UserRank> users = new ArrayList<>();
        users.add(new UserRank(1,"Mateo","Czapeo","FC Bobry",230));
        users.add(new UserRank(2,"Mateusz","Gładysz","FC Żubry",229));
        users.add(new UserRank(3,"Kamil","Nowakowski","FC Krowy",228));
        users.add(new UserRank(4,"Krystian","Nowak","FC Anakondy",222));
        users.add(new UserRank(5,"Kornel","Makuszewski","FC Antylopy",221));
        users.add(new UserRank(6,"Kacper","Kura","FC Borsuki",216));
        users.add(new UserRank(7,"Sebastian","Krzywonos","FC Bizony",215));
        users.add(new UserRank(8,"Marcel","Jakubiak","FC Czajki",214));
        users.add(new UserRank(9,"Jakub","Janda","FC Daniele",213));
        users.add(new UserRank(10,"Filip","Hajzerka","FC Drozdy",212));
        users.add(new UserRank(11,"Tomek","Polak","FC Emu",211));
        users.add(new UserRank(12,"Jan","Kazimierz","FC Fretki",210));
        users.add(new UserRank(13,"Kazimierz","Kiełkowiec","FC Gawrony",190));
        users.add(new UserRank(14,"Paweł","Curyszewski","FC Hieny",189));
        users.add(new UserRank(15,"Robert","Burek","FC Iglaki",188));
        users.add(new UserRank(16,"Aleksander","Opolski","FC Jelenie",188));
        users.add(new UserRank(17,"Bartłomiej","Frankenstein","FC Kormorany",187));
        users.add(new UserRank(18,"Bartosz","Gnom","FC Konary",186));
        users.add(new UserRank(19,"Czesław","Ozil","FC Lemury",185));
        users.add(new UserRank(20,"Daniel","Lajkonik","FC Lampardy",184));
        users.add(new UserRank(21,"Damian","Drabina","FC Lwy",183));
        users.add(new UserRank(22,"Darek","Dreptak","FC Meduzy",182));
        users.add(new UserRank(23,"Franek","Kimonowski","FC Morświny",181));
        users.add(new UserRank(24,"Eugeniusz","Czajka","FC Nasturcje",179));
        users.add(new UserRank(25,"Eustachy","Gniew","FC Oposy",178));
        users.add(new UserRank(26,"Gaweł","Stomil","FC Bugi",177));
        users.add(new UserRank(27,"Henryk","Poduszewski","FC Lipy",176));
        users.add(new UserRank(28,"Hiacynt","Kwiatkowski","FC Gepardy",174));
        users.add(new UserRank(29,"Ireneusz","Sarna","FC Słonie",173));
        users.add(new UserRank(30,"Jędrzej","Bug","FC Żyrafy",172));
        return users;
    }
}
