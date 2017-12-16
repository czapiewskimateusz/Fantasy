package com.example.mateusz.fantasy.team.view;

import com.example.mateusz.fantasy.team.model.repo.Player;

import java.util.ArrayList;

public interface ITransferView {
    void showProgress(boolean show);
    void presentAllPlayers(ArrayList<Player> allPlayers);
    void onGetAllPlayersFailure();
}
