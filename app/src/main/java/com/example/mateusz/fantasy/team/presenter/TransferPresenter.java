package com.example.mateusz.fantasy.team.presenter;


import com.example.mateusz.fantasy.team.model.API.GetAllPlayersAPI;
import com.example.mateusz.fantasy.team.model.repo.Player;
import com.example.mateusz.fantasy.team.view.ITransferView;

import java.util.ArrayList;

public class TransferPresenter {
    private ITransferView view;
    private GetAllPlayersAPI getAllPlayersAPI;

    public TransferPresenter(ITransferView view) {
        this.view = view;
        getAllPlayersAPI = new GetAllPlayersAPI(this);
    }

    public void getAllPlayers(){
        view.showProgress(true);
        getAllPlayersAPI.getAllPlayers();
    }

    public void onGetAllPlayersFailure(){
        view.onGetAllPlayersFailure();
    }

    public void onGetAllPlayersSuccess(ArrayList<Player> allPlayers){
        view.showProgress(false);
        view.presentAllPlayers(allPlayers);
    }
}
