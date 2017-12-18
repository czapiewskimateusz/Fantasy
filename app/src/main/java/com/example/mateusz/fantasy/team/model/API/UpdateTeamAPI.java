package com.example.mateusz.fantasy.team.model.API;

import com.example.mateusz.fantasy.authentication.register.model.Response;
import com.example.mateusz.fantasy.team.model.repo.Player;
import com.example.mateusz.fantasy.team.model.webservices.TeamWebService;
import com.example.mateusz.fantasy.team.presenter.TransferPresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static com.example.mateusz.fantasy.utils.NetworkUtils.getRetrofitInstance;

public class UpdateTeamAPI implements Callback<Response> {
    private TransferPresenter transferPresenter;

    public UpdateTeamAPI(TransferPresenter teamPresenter) {
        this.transferPresenter = teamPresenter;
    }

    public void updateTeam(int teamId,int userId,float budget, ArrayList<Player> userTeam) {
        Retrofit retrofit = getRetrofitInstance();
        TeamWebService api = retrofit.create(TeamWebService.class);
        Call<Response> call = api.updateTeam(teamId,
                userId,
                budget,
                userTeam.get(0).getPlayerId(),
                userTeam.get(1).getPlayerId(),
                userTeam.get(2).getPlayerId(),
                userTeam.get(3).getPlayerId(),
                userTeam.get(4).getPlayerId(),
                userTeam.get(5).getPlayerId(),
                userTeam.get(6).getPlayerId(),
                userTeam.get(7).getPlayerId(),
                userTeam.get(8).getPlayerId(),
                userTeam.get(9).getPlayerId(),
                userTeam.get(10).getPlayerId());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        transferPresenter.onUpdateSuccess();
    }

    @Override
    public void onFailure(Call<Response> call, Throwable t) {
        transferPresenter.onUpdateFailure();
    }
}
