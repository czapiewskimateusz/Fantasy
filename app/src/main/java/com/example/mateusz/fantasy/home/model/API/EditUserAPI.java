package com.example.mateusz.fantasy.home.model.API;

import com.example.mateusz.fantasy.home.model.repo.ServerResponse;
import com.example.mateusz.fantasy.home.model.webService.HomeWebService;
import com.example.mateusz.fantasy.home.presenter.UserDetailPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.mateusz.fantasy.utils.NetworkUtils.getRetrofitInstance;


public class EditUserAPI implements Callback<ServerResponse>{

    private UserDetailPresenter userDetailPresenter;

    public EditUserAPI(UserDetailPresenter userDetailPresenter) {
        this.userDetailPresenter = userDetailPresenter;
    }

    public void editUser(String userEmail, String email, String firstName, String lastName, String newPassword) {
        Retrofit retrofit = getRetrofitInstance();
        HomeWebService api = retrofit.create(HomeWebService.class);
        Call<ServerResponse> call = api.editUser(userEmail,email,firstName,lastName,newPassword);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
        if (response.isSuccessful()){
            userDetailPresenter.onResponse(response.body());
        }
    }

    @Override
    public void onFailure(Call<ServerResponse> call, Throwable t) {
        userDetailPresenter.onConnectionError();
    }
}
