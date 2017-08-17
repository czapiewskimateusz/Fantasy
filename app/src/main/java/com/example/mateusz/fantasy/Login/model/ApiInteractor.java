package com.example.mateusz.fantasy.Login.model;


import com.example.mateusz.fantasy.Login.presenter.LoginPresenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mateusz.fantasy.Login.presenter.LoginPresenter.ERROR_TAG_INCORRECT_PASSWORD;
import static com.example.mateusz.fantasy.Login.presenter.LoginPresenter.ERROR_TAG_USER_DOESNT_EXIST;

/**
 * Created by Mateusz on 15.08.2017.
 */

public class ApiInteractor implements Callback<User> {

    static final String BASE_URL = "http://fantasypl.c0.pl/";
    private String password;
    private LoginPresenter loginPresenter;

    public ApiInteractor(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    public void login(String email, String password) {
        this.password = password;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        LoginWebService api = retrofit.create(LoginWebService.class);

        Call<User> call = api.getUser("\""+email+"\"");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {

        if (response.isSuccessful()) {

            if (response.body().getPassword().equals(password)){
                loginPresenter.onLoginSuccessful();
            } else {
                loginPresenter.onLoginUnsuccessful(ERROR_TAG_INCORRECT_PASSWORD);
            }

        }

    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {

        loginPresenter.onLoginUnsuccessful(ERROR_TAG_USER_DOESNT_EXIST);

    }
}
