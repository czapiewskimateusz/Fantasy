package com.example.mateusz.fantasy.leagues.view;

import com.example.mateusz.fantasy.leagues.model.repo.League;

import java.util.List;


public interface ILeagueView {

    void showProgress(boolean show);

    void presentLeagues(List<League> leagues);

    void reloadLeagues();

    void onJoinLeagueFailure();

    void onCreateLeagueFailure();

    void setRefreshing(boolean set);

    void onGetLeaguesFailure();
}
