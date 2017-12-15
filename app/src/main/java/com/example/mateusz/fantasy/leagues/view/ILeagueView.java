package com.example.mateusz.fantasy.leagues.view;

import com.example.mateusz.fantasy.leagues.model.repo.League;

import java.util.List;


public interface ILeagueView {

    public void showProgress(boolean show);

    public void presentLeagues(List<League> leagues);

    public void reloadLeagues();

    public void onJoinLeagueFailure();

    public void onCreateLeagueFailure();

    public void setRefreshing(boolean set);
}