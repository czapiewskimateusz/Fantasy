package com.example.mateusz.fantasy.home.view.fragment;

import com.example.mateusz.fantasy.home.model.repo.League;

import java.util.List;


public interface ILeagueView {

    public void showProgress(boolean show);

    public void presentLeagues(List<League> leagues);

    public void reloadLeagues();

    public void onJoinLeagueFailure();

    public void onCreateLeagueFailure();
}
