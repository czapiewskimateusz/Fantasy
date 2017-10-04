package com.example.mateusz.fantasy.home.view;

import com.example.mateusz.fantasy.home.model.repo.UserRank;

import java.util.List;

public interface ILeagueDetailView {

    public void showProgress(boolean show);

    public void presentUsersRank(List<UserRank> usersRank);

    public void onConnectionError();

}
