package com.example.mateusz.fantasy.Home.presenter;

import android.util.Log;

import com.example.mateusz.fantasy.Home.model.League;
import com.example.mateusz.fantasy.Home.model.LeagueApiInteractor;
import com.example.mateusz.fantasy.Home.view.Fragment.ILeagueView;

import java.util.List;


public class LeaguePresenter {

    public static final String LEAGUE_BUNDLE_EXTRA = "leagueBundleExtra";

    /**
     * Dependencies
     */
    private ILeagueView view;
    private final LeagueApiInteractor mApiinteractor;

    /**
     * Constructor
     */
    public LeaguePresenter(ILeagueView view) {

        this.view = view;
        mApiinteractor = new LeagueApiInteractor(this);

    }

    public void getUserLeagues(int userId, int totalPoints){
        view.showProgress(true);
        mApiinteractor.getUserLeagues(userId,totalPoints);
    }

    public void presentLeagues(List<League> leagues){

        view.presentLeagues(leagues);
        view.showProgress(false);

    }

    public void onGetLeaguesFailure(String message){
        view.showProgress(false);
        Log.d("RETRO ERROR", message);
    }

    public void joinLeague(String leagueCode){}

    public void createLeague(String leagueName){}
}
