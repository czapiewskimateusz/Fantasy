package com.example.mateusz.fantasy.team.presenter;


import android.content.Context;

import com.example.mateusz.fantasy.team.model.API.GetAllPlayersAPI;
import com.example.mateusz.fantasy.team.model.API.UpdateTeamAPI;
import com.example.mateusz.fantasy.team.model.repo.Player;
import com.example.mateusz.fantasy.team.view.ITransferView;

import java.util.ArrayList;

public class TransferPresenter {
    private ITransferView view;
    private GetAllPlayersAPI getAllPlayersAPI;
    private UpdateTeamAPI updateTeamAPI;
    private Context context;

    public TransferPresenter(ITransferView view, Context context) {
        this.view = view;
        this.context = context;
        getAllPlayersAPI = new GetAllPlayersAPI(this);
        updateTeamAPI = new UpdateTeamAPI(this);
    }

    public void getAllPlayers(){
        view.showProgress(true);
        getAllPlayersAPI.getAllPlayers();
    }

    public void updateTeam(int teamId,int userId, float budget, ArrayList<Player> userTeam){
        updateTeamAPI.updateTeam(teamId,userId,budget,userTeam);
    }

    public void onGetAllPlayersFailure(){
        view.onGetAllPlayersFailure();
    }

    public void onGetAllPlayersSuccess(ArrayList<Player> allPlayers){
        view.showProgress(false);
        view.presentAllPlayers(allPlayers);
    }

    public void onUpdateSuccess() {
        view.onUpdateSuccess();
    }

    public void onUpdateFailure() {
        view.onUpdateFailure();
    }
}
