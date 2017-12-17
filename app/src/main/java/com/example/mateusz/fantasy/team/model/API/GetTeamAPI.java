package com.example.mateusz.fantasy.team.model.API;

import com.example.mateusz.fantasy.team.model.repo.Player;
import com.example.mateusz.fantasy.team.model.repo.PlayerJSONResponse;
import com.example.mateusz.fantasy.team.model.webservices.TeamWebService;
import com.example.mateusz.fantasy.team.presenter.TeamPresenter;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.mateusz.fantasy.utils.NetworkUtils.getRetrofitInstance;

public class GetTeamAPI implements Callback<PlayerJSONResponse> {
    private TeamPresenter teamPresenter;

    public GetTeamAPI(TeamPresenter teamPresenter) {
        this.teamPresenter = teamPresenter;
    }

    public void getUsersTeam(int teamId){
        Retrofit retrofit = getRetrofitInstance();
        TeamWebService api = retrofit.create(TeamWebService.class);
        Call<PlayerJSONResponse> call = api.getUsersTeam(teamId);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<PlayerJSONResponse> call, Response<PlayerJSONResponse> response) {
        ArrayList<Player> userTeam = new ArrayList<>(Arrays.asList(response.body().getPlayers()));
        teamPresenter.onGetUserTeamSuccess(userTeam);
    }

    @Override
    public void onFailure(Call<PlayerJSONResponse> call, Throwable t) {
        teamPresenter.onGetUserTeamFailure();
    }
}
