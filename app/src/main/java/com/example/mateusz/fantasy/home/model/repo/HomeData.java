package com.example.mateusz.fantasy.home.model.repo;

public class HomeData {

    int gw;
    int yourScore;
    int max;
    int avg;
    String deadline;

    public HomeData(int gw, int yourScore, int max, int avg, String deadline) {
        this.gw = gw;
        this.yourScore = yourScore;
        this.max = max;
        this.avg = avg;
        this.deadline = deadline;
    }

    public int getGw() {
        return gw;
    }

    public void setGw(int gw) {
        this.gw = gw;
    }

    public int getYourScore() {
        return yourScore;
    }

    public void setYourScore(int yourScore) {
        this.yourScore = yourScore;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
