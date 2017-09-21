package com.example.mateusz.fantasy.Home.presenter;

import android.content.Context;
import com.example.mateusz.fantasy.Home.view.IHomeView;


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

}
