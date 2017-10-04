package com.example.mateusz.fantasy.home.presenter;

import com.example.mateusz.fantasy.authentication.login.model.User;
import com.example.mateusz.fantasy.home.model.API.HomeApiInteractor;

public class HomePresenter {

    private final HomeApiInteractor mHomeApiInteractor;

    public HomePresenter() {
        mHomeApiInteractor = new HomeApiInteractor(this);
    }

    public void initUser(int userId){
        mHomeApiInteractor.getUser(userId);
    }

    public void onGetUserSuccess(User user){

    }

    public void onGetUserFailure(String message){

    }
}
