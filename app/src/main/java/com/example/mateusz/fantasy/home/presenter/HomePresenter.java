package com.example.mateusz.fantasy.home.presenter;

import android.util.Log;

import com.example.mateusz.fantasy.home.model.API.HomeApiInteractor;
import com.example.mateusz.fantasy.home.model.repo.HomeUser;
import com.example.mateusz.fantasy.home.view.fragment.IHomeView;

public class HomePresenter {

    private final HomeApiInteractor mHomeApiInteractor;
    private final IHomeView view;

    public HomePresenter(IHomeView view) {
        this.view = view;
        mHomeApiInteractor = new HomeApiInteractor(this);
    }

    public void initUser(int userId){

        mHomeApiInteractor.getUser(userId);

    }

    public void onGetUserSuccess(HomeUser user){

        view.getUser(user);

    }

    public void onGetUserFailure(String message){

        view.showConnectionError();

    }
}
