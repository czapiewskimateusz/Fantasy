package com.example.mateusz.fantasy.leagues.view;

import com.example.mateusz.fantasy.leagues.model.repo.UserRank;

import java.util.List;

public interface ILeagueDetailView {

    void showProgress(boolean show);

    void presentUsersRank(List<UserRank> usersRank);

    void onConnectionError();

}
