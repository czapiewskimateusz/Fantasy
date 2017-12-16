package com.example.mateusz.fantasy.team.model.API;

import com.example.mateusz.fantasy.team.model.repo.PlayerJSONResponse;
import com.example.mateusz.fantasy.team.presenter.TeamPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTeamAPI implements Callback<PlayerJSONResponse> {
    private TeamPresenter teamPresenter;

    public GetTeamAPI(TeamPresenter teamPresenter) {
        this.teamPresenter = teamPresenter;
    }

    @Override
    public void onResponse(Call<PlayerJSONResponse> call, Response<PlayerJSONResponse> response) {

    }

    @Override
    public void onFailure(Call<PlayerJSONResponse> call, Throwable t) {

    }
}
