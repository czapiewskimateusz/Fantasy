package com.example.mateusz.fantasy.authentication.login.model;


import com.example.mateusz.fantasy.authentication.login.presenter.LoginPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


import static com.example.mateusz.fantasy.authentication.login.presenter.LoginPresenter.ERROR_TAG_INCORRECT_PASSWORD;
import static com.example.mateusz.fantasy.authentication.login.presenter.LoginPresenter.ERROR_TAG_USER_DOESNT_EXIST;
import static com.example.mateusz.fantasy.utils.NetworkUtils.getRetrofitInstance;


public class LoginApiInteractor implements Callback<User> {

    private String password;
    private LoginPresenter loginPresenter;

    public LoginApiInteractor(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    public void login(String email, String password) {
        this.password = password;
        Retrofit retrofit = getRetrofitInstance();
        LoginWebService api = retrofit.create(LoginWebService.class);
        Call<User> call = api.getUser("\""+email+"\"");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.isSuccessful()) {
            if (response.body().getPassword().equals(password))
                loginPresenter.onLoginSuccessful(response.body().getUserId(),response.body().getTotalPoints());
             else
                loginPresenter.onLoginUnsuccessful(ERROR_TAG_INCORRECT_PASSWORD);
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        loginPresenter.onLoginUnsuccessful(ERROR_TAG_USER_DOESNT_EXIST);
    }
}
