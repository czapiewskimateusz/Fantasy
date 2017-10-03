package com.example.mateusz.fantasy.Home.presenter;

import com.example.mateusz.fantasy.Authentication.Login.model.User;
import com.example.mateusz.fantasy.Home.model.HomeApiInteractor;

/**
 * Created by Ready4s on 03.10.2017.
 */

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
