package com.example.mateusz.fantasy.team.presenter;

import com.example.mateusz.fantasy.team.model.API.GetTeamAPI;
import com.example.mateusz.fantasy.team.view.ITeamView;

public class TeamPresenter {

    private final ITeamView view;
    private final GetTeamAPI getTeamAPI;

    public TeamPresenter(ITeamView view) {
        this.view = view;
        getTeamAPI = new GetTeamAPI(this);
    }

    public void getUserTeam(int userId){

    }

    public void onGetUserTeamFailure(){

    }
}
