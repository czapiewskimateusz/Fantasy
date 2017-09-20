package com.example.mateusz.fantasy.Home.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mateusz.fantasy.Home.model.League;
import com.example.mateusz.fantasy.Home.view.IHomeView;
import com.example.mateusz.fantasy.Home.view.LeagueDetailActivity;

import static com.example.mateusz.fantasy.Home.model.League.CODE;
import static com.example.mateusz.fantasy.Home.model.League.LEAGUE_ID;
import static com.example.mateusz.fantasy.Home.model.League.NAME;
import static com.example.mateusz.fantasy.Home.model.League.NUMBER_OF_PLAYERS;
import static com.example.mateusz.fantasy.Home.model.League.USER_POSITION;

/**
 * Created by Mateusz on 20.09.2017.
 */

public class LeaguePresenter {

    public static final String LEAGUE_BUNDLE_EXTRA = "leagueBundleExtra";

    /**
     * Dependencies
     */
    private IHomeView view;
    private Context context;
    //TODO private final LoginApiInteractor mApiinteractor;

    /**
     * Constructor
     */
    public LeaguePresenter() {

        //TODO mApiinteractor = new LoginApiInteractor(this);

    }

    public void onViewAttached(Context context, IHomeView view) {

        this.view = view;
        this.context = context;

    }

    public void onViewDetached() {

        view = null;
        context = null;

    }

    public void startLeagueDetailActivity(League league){

        Intent intent = new Intent(context, LeagueDetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt(LEAGUE_ID,league.getLeague_id());
        bundle.putString(NAME,league.getName());
        bundle.putInt(USER_POSITION,league.getUserPosition());
        bundle.putString(CODE,league.getCode());
        bundle.putInt(NUMBER_OF_PLAYERS,league.getNumberOfPlayers());

        intent.putExtra(LEAGUE_BUNDLE_EXTRA,bundle);
        view.startLeagueDetailActivity(intent);
    }
}
