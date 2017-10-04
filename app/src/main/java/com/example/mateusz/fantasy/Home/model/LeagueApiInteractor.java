package com.example.mateusz.fantasy.Home.model;

import com.example.mateusz.fantasy.Home.presenter.LeaguePresenter;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.mateusz.fantasy.Utils.NetworkUtils.getRetrofitInstance;

public class LeagueApiInteractor implements Callback<LeagueJsonResponse> {

    private LeaguePresenter mLeaguePresenter;

    /**
     * Constructor
     * @param mLeaguePresenter presenter callback
     */
    public LeagueApiInteractor(LeaguePresenter mLeaguePresenter) {
        this.mLeaguePresenter = mLeaguePresenter;
    }

    /**
     * Get all leagues in which user participates
     * @param userId user's id
     * @param totalPoints user's score
     */
    public void getUserLeagues(int userId, int totalPoints){

        Retrofit retrofit = getRetrofitInstance();

        LeagueWebService api = retrofit.create(LeagueWebService.class);
        Call<LeagueJsonResponse> call = api.getLeagues(userId,totalPoints);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<LeagueJsonResponse> call, Response<LeagueJsonResponse> response) {

        if (response.isSuccessful()){

            ArrayList<League> leagues = new ArrayList<>(Arrays.asList(response.body().getLeagues()));
            mLeaguePresenter.presentLeagues(leagues);
        }

    }

    @Override
    public void onFailure(Call<LeagueJsonResponse> call, Throwable t) {

        mLeaguePresenter.onGetLeaguesFailure(t.getMessage());

    }
}
