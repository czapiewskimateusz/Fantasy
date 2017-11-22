package com.example.mateusz.fantasy.home.model.API;


import com.example.mateusz.fantasy.home.model.repo.LeagueDetailJsonResponse;
import com.example.mateusz.fantasy.home.model.repo.UserRank;
import com.example.mateusz.fantasy.home.model.webService.LeagueWebService;
import com.example.mateusz.fantasy.home.presenter.LeagueDetailPresenter;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.mateusz.fantasy.utils.NetworkUtils.getRetrofitInstance;

public class GetLeagueDetailAPI implements Callback<LeagueDetailJsonResponse> {

    private LeagueDetailPresenter leagueDetailPresenter;

    public GetLeagueDetailAPI(LeagueDetailPresenter leagueDetailPresenter) {
        this.leagueDetailPresenter = leagueDetailPresenter;
    }

    public void getUsersRank(int leagueId) {
        Retrofit retrofit = getRetrofitInstance();
        LeagueWebService api = retrofit.create(LeagueWebService.class);
        Call<LeagueDetailJsonResponse> call = api.getUsersRank(leagueId);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<LeagueDetailJsonResponse> call, Response<LeagueDetailJsonResponse> response) {
        if (response.isSuccessful()){
            ArrayList<UserRank> usersRank = new ArrayList<>(Arrays.asList(response.body().getUsersRank()));
            leagueDetailPresenter.presentUsersRank(usersRank);
        }
    }

    @Override
    public void onFailure(Call<LeagueDetailJsonResponse> call, Throwable t) {
        leagueDetailPresenter.onGetUsersRankFailure(t.getMessage());
    }
}
