package com.example.mateusz.fantasy.authentication.register.model;

import com.example.mateusz.fantasy.authentication.register.presenter.RegisterPresenter;
import static com.example.mateusz.fantasy.utils.NetworkUtils.getRetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class RegisterApiInteractor implements Callback<Response> {

    public static final String MESSAGE_SUCCESSFUL = "success";
    public static final String MESSAGE_FAILURE = "failed";

    private RegisterPresenter registerPresenter;

    public RegisterApiInteractor(RegisterPresenter registerPresenter) {
        this.registerPresenter = registerPresenter;
    }

    public void register(String email, String firstname, String lastname, String teamName, String password){
        Retrofit retrofit = getRetrofitInstance();
        RegisterWebService api = retrofit.create(RegisterWebService.class);
        Call<Response> call = api.registerUser("\""+email+"\"","\""+password+"\"","\""+firstname+"\"","\""+lastname+"\"","\""+teamName+"\"");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        if (response.isSuccessful()) {
            if (response.body().getMessage().equals(MESSAGE_SUCCESSFUL))
                registerPresenter.onRegisterSuccess();
             else if (response.body().getMessage().equals(MESSAGE_FAILURE))
                registerPresenter.onRegisterFailure();
        }
    }

    @Override
    public void onFailure(Call<Response> call, Throwable t) {
        registerPresenter.onConnectionError();
    }
}
