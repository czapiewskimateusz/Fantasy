package com.example.mateusz.fantasy.team.view;

import com.example.mateusz.fantasy.team.model.repo.Player;

import java.util.ArrayList;

public interface ITeamView {
    void showProgress(boolean show);
    void presentTeam(ArrayList<Player> userTeam);
    void onGetTeamFailure();
}
