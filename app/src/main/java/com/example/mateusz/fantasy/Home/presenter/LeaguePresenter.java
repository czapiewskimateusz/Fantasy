package com.example.mateusz.fantasy.Home.presenter;

import android.util.Log;


public class LeaguePresenter {

    public static final String LEAGUE_BUNDLE_EXTRA = "leagueBundleExtra";

    /**
     * Dependencies
     */
   // private ILeagueView view;
    //TODO private final LoginApiInteractor mApiinteractor;

    /**
     * Constructor
     */
    public LeaguePresenter() {

        //TODO mApiinteractor = new LoginApiInteractor(this);

    }


    public void joinLeague(String leagueCode){
        Log.d("PRESENTER LUKS CODE", leagueCode);
    }

    public void createLeague(String leagueName){
        Log.d("PRESENTER LUKS NAME", leagueName);
    }
}
