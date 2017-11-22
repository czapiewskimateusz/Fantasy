package com.example.mateusz.fantasy.home.model.API;


import com.example.mateusz.fantasy.home.model.repo.HomeData;
import com.example.mateusz.fantasy.home.model.webService.HomeWebService;
import com.example.mateusz.fantasy.home.presenter.HomePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.mateusz.fantasy.utils.NetworkUtils.getRetrofitInstance;

public class GetHomeDataAPI implements Callback<HomeData> {

    private HomePresenter mHomePresenter;

    public GetHomeDataAPI(HomePresenter homePresenter) {
        this.mHomePresenter = homePresenter;
    }

    public void getData(int teamId) {
        Retrofit retrofit = getRetrofitInstance();
        HomeWebService api = retrofit.create(HomeWebService.class);
        Call<HomeData> call = api.getData(teamId);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<HomeData> call, Response<HomeData> response) {
        if (response.isSuccessful())
            mHomePresenter.onGetHomeDataSuccess(response.body());
    }

    @Override
    public void onFailure(Call<HomeData> call, Throwable t) {
            mHomePresenter.onGetDataFailure();
    }
}
