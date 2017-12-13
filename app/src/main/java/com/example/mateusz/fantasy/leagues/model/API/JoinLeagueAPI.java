package com.example.mateusz.fantasy.leagues.model.API;

import com.example.mateusz.fantasy.authentication.register.model.Response;
import com.example.mateusz.fantasy.leagues.view.LeaguePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static com.example.mateusz.fantasy.authentication.register.model.RegisterApiInteractor.MESSAGE_FAILURE;
import static com.example.mateusz.fantasy.authentication.register.model.RegisterApiInteractor.MESSAGE_SUCCESSFUL;
import static com.example.mateusz.fantasy.utils.NetworkUtils.getRetrofitInstance;


public class JoinLeagueAPI implements Callback<Response> {

    private LeaguePresenter mLeaguePresenter;

    public JoinLeagueAPI(LeaguePresenter mLeaguePresenter) {
        this.mLeaguePresenter = mLeaguePresenter;
    }

    /**
     * Join league with given league code
     * @param leagueCode password to join the league
     * @param userId user's id
     */
    public void joinLeague(String leagueCode, int userId) {
        Retrofit retrofit = getRetrofitInstance();
        LeagueWebService api = retrofit.create(LeagueWebService.class);
        Call<Response> call = api.joinLeague(userId, leagueCode);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        if (response.isSuccessful()){
            if (response.body().getMessage().equals(MESSAGE_SUCCESSFUL))
                mLeaguePresenter.onJoinLeagueSuccess();
             else if (response.body().getMessage().equals(MESSAGE_FAILURE))
                mLeaguePresenter.onJoinLeagueFailure();
        }
    }

    @Override
    public void onFailure(Call<Response> call, Throwable t) {
        //TODO
    }
}
