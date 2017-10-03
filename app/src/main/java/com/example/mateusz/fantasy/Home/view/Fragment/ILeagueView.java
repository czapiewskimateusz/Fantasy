package com.example.mateusz.fantasy.Home.view.Fragment;

import com.example.mateusz.fantasy.Home.model.League;

import java.util.List;


public interface ILeagueView {

    public void showProgress(boolean show);

    public void presentLeagues(List<League> leagues);
}
