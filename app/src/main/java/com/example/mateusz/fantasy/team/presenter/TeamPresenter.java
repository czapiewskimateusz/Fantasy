package com.example.mateusz.fantasy.team.presenter;

import com.example.mateusz.fantasy.team.model.API.GetTeamAPI;
import com.example.mateusz.fantasy.team.model.repo.Player;
import com.example.mateusz.fantasy.team.view.ITeamView;

import java.util.ArrayList;

public class TeamPresenter {

    private final ITeamView view;
    private final GetTeamAPI getTeamAPI;

    public TeamPresenter(ITeamView view) {
        this.view = view;
        getTeamAPI = new GetTeamAPI(this);
    }

    public void getUserTeam(int teamId){
        view.showProgress(true);
        getTeamAPI.getUsersTeam(teamId);
    }

    public void onGetUserTeamFailure(){
        view.onGetTeamFailure();
    }

    public void onGetUserTeamSuccess(ArrayList<Player> userTeam){
        view.showProgress(false);
        view.presentTeam(userTeam);
    }
}
