package com.example.mateusz.fantasy.Home.view;

import com.example.mateusz.fantasy.Home.model.UserRank;

import java.util.List;

public interface ILeagueDetailView {

    public void showProgress(boolean show);

    public void presentUsersRank(List<UserRank> usersRank);

    public void onConnectionError();

}
