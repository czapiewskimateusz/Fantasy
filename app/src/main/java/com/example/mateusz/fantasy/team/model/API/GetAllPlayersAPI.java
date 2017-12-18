package com.example.mateusz.fantasy.team.model.API;

import com.example.mateusz.fantasy.team.model.repo.Player;
import com.example.mateusz.fantasy.team.model.repo.PlayerJSONResponse;
import com.example.mateusz.fantasy.team.model.webservices.TeamWebService;
import com.example.mateusz.fantasy.team.presenter.TeamPresenter;
import com.example.mateusz.fantasy.team.presenter.TransferPresenter;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.mateusz.fantasy.utils.NetworkUtils.getRetrofitInstance;

public class GetAllPlayersAPI implements Callback<PlayerJSONResponse> {
    private TransferPresenter teamPresenter;

    public GetAllPlayersAPI(TransferPresenter teamPresenter) {
        this.teamPresenter = teamPresenter;
    }

    public void getAllPlayers(){
        Retrofit retrofit = getRetrofitInstance();
        TeamWebService api = retrofit.create(TeamWebService.class);
        Call<PlayerJSONResponse> call = api.getAllPlayers();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<PlayerJSONResponse> call, Response<PlayerJSONResponse> response) {
        if (response.isSuccessful()){
            ArrayList<Player> allPlayers = new ArrayList<>(Arrays.asList(response.body().getPlayers()));
            teamPresenter.onGetAllPlayersSuccess(allPlayers);
        }
    }

    @Override
    public void onFailure(Call<PlayerJSONResponse> call, Throwable t) {
        teamPresenter.onGetAllPlayersFailure();
    }
}
