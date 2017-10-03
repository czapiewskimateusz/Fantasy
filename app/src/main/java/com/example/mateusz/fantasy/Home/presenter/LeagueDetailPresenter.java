package com.example.mateusz.fantasy.Home.presenter;


import android.util.Log;

import com.example.mateusz.fantasy.Home.model.LeagueDetailApiInteractor;
import com.example.mateusz.fantasy.Home.model.UserRank;
import com.example.mateusz.fantasy.Home.view.ILeagueDetailView;

import java.util.List;

public class LeagueDetailPresenter {

    /**
     * Dependencies
     */
    private ILeagueDetailView view;
    private final LeagueDetailApiInteractor mApiinteractor;

    /**
     * Constructor
     */
    public LeagueDetailPresenter(ILeagueDetailView view) {

        this.view = view;
        mApiinteractor = new LeagueDetailApiInteractor(this);

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
