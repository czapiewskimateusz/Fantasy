package com.example.mateusz.fantasy.leagues.presenter;


import android.util.Log;

import com.example.mateusz.fantasy.leagues.view.ILeagueDetailView;
import com.example.mateusz.fantasy.leagues.model.API.GetLeagueDetailAPI;
import com.example.mateusz.fantasy.leagues.model.repo.UserRank;

import java.util.List;

public class LeagueDetailPresenter {

    /**
     * Dependencies
     */
    private ILeagueDetailView view;
    private final GetLeagueDetailAPI mApiinteractor;

    /**
     * Constructor
     */
    public LeagueDetailPresenter(ILeagueDetailView view) {
        this.view = view;
        mApiinteractor = new GetLeagueDetailAPI(this);
    }

    public void getUsersRank(int leagueId){
        view.showProgress(true);
        mApiinteractor.getUsersRank(leagueId);
    }

    public void presentUsersRank(List<UserRank> usersRank){
        view.presentUsersRank(usersRank);
        view.showProgress(false);
    }

    public void onGetUsersRankFailure(String message){
        view.showProgress(false);
        Log.d("RETRO ERROR", message);
        view.onConnectionError();
    }
}
