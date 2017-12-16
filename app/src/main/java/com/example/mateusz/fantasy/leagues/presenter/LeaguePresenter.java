package com.example.mateusz.fantasy.leagues.presenter;


import com.example.mateusz.fantasy.leagues.model.API.CreateLeagueAPI;
import com.example.mateusz.fantasy.leagues.model.API.GetLeaguesAPI;
import com.example.mateusz.fantasy.leagues.model.API.JoinLeagueAPI;
import com.example.mateusz.fantasy.leagues.model.repo.League;
import com.example.mateusz.fantasy.leagues.view.ILeagueView;

import java.util.List;


public class LeaguePresenter {

    public static final String LEAGUE_BUNDLE_EXTRA = "leagueBundleExtra";

    /**
     * Dependencies
     */
    private ILeagueView view;
    private final GetLeaguesAPI mGetLeaguesAPI;
    private final JoinLeagueAPI mJoinLeagueAPI;
    private final CreateLeagueAPI mCreateLeagueAPI;

    /**
     * Constructor
     */
    public LeaguePresenter(ILeagueView view) {
        this.view = view;
        mGetLeaguesAPI = new GetLeaguesAPI(this);
        mJoinLeagueAPI = new JoinLeagueAPI(this);
        mCreateLeagueAPI = new CreateLeagueAPI(this);
    }

    /**
     * Get leagues in which user participates
     * @param userId users id
     * @param totalPoints users total points
     */
    public void getUserLeagues(int userId, int totalPoints) {
        view.showProgress(true);
        mGetLeaguesAPI.getUserLeagues(userId, totalPoints);
    }

    /**
     * Present leagues received from API
     * @param leagues list of leagues
     */
    public void presentLeagues(List<League> leagues) {
        view.presentLeagues(leagues);
        view.showProgress(false);
        view.setRefreshing(false);
    }

    /**
     * Show error during receiving leagues
     */
    public void onGetLeaguesFailure() {
        view.showProgress(false);
        view.onGetLeaguesFailure();
    }

    /**
     * Join logged user to a league with given code
     * @param leagueCode code to enter the league
     * @param userId users id
     */
    public void joinLeague(String leagueCode, int userId) {
        mJoinLeagueAPI.joinLeague(leagueCode,userId);
    }

    public void onJoinLeagueSuccess(){
        view.reloadLeagues();
    }

    public void onJoinLeagueFailure(){
        view.onJoinLeagueFailure();
    }

    public void createLeague(String leagueName, int userId) {
        mCreateLeagueAPI.createLeague(leagueName, userId);
    }

    public void onCreateLeagueSuccess(){
        view.reloadLeagues();
    }

    public void onCreateLeagueFailure(){
        view.onCreateLeagueFailure();
    }

}
