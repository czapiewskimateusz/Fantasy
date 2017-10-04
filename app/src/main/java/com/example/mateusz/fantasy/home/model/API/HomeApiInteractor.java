package com.example.mateusz.fantasy.home.model.API;

import com.example.mateusz.fantasy.authentication.login.model.User;
import com.example.mateusz.fantasy.home.model.webService.HomeWebService;
import com.example.mateusz.fantasy.home.presenter.HomePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.mateusz.fantasy.utils.NetworkUtils.getRetrofitInstance;

public class HomeApiInteractor implements Callback<User> {

    private HomePresenter mHomePresenter;

    public HomeApiInteractor(HomePresenter mHomePresenter) {
        this.mHomePresenter = mHomePresenter;
    }

    public void getUser(int userId) {

        Retrofit retrofit = getRetrofitInstance();

        HomeWebService api = retrofit.create(HomeWebService.class);
        Call<User> call = api.getUser(userId);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {

        if (response.isSuccessful()){
            mHomePresenter.onGetUserSuccess(response.body());
        }

    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        mHomePresenter.onGetUserFailure("Something went wrong");
    }
}
