package com.example.mateusz.fantasy.leagues.model.API;

import com.example.mateusz.fantasy.authentication.register.model.Response;
import com.example.mateusz.fantasy.leagues.view.LeaguePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static com.example.mateusz.fantasy.authentication.register.model.RegisterApiInteractor.MESSAGE_FAILURE;
import static com.example.mateusz.fantasy.authentication.register.model.RegisterApiInteractor.MESSAGE_SUCCESSFUL;
import static com.example.mateusz.fantasy.utils.NetworkUtils.getRetrofitInstance;


public class CreateLeagueAPI implements Callback<Response> {

    private LeaguePresenter mLeaguePresenter;

    public CreateLeagueAPI(LeaguePresenter mLeaguePresenter) {
        this.mLeaguePresenter = mLeaguePresenter;
    }

    public void createLeague(String leagueName, int userId){
        Retrofit retrofit = getRetrofitInstance();
        LeagueWebService api = retrofit.create(LeagueWebService.class);
        Call<Response> call = api.createLeague(leagueName, userId);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        if (response.isSuccessful()){
            if (response.body().getMessage().equals(MESSAGE_SUCCESSFUL))
                mLeaguePresenter.onCreateLeagueSuccess();
             else if (response.body().getMessage().equals(MESSAGE_FAILURE))
                mLeaguePresenter.onCreateLeagueFailure();
        }
    }

    @Override
    public void onFailure(Call<Response> call, Throwable t) {
        mLeaguePresenter.onCreateLeagueFailure();
    }
}
