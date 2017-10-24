package com.example.mateusz.fantasy.home.presenter;

import com.example.mateusz.fantasy.home.model.API.GetHomeDataAPI;
import com.example.mateusz.fantasy.home.model.API.GetHomeUserDataAPI;
import com.example.mateusz.fantasy.home.model.repo.HomeData;
import com.example.mateusz.fantasy.home.model.repo.HomeUser;
import com.example.mateusz.fantasy.home.view.fragment.IHomeView;

public class HomePresenter {

    private final GetHomeUserDataAPI mGetHomeUserDataAPI;
    private final GetHomeDataAPI mGetHomeDataAPI;
    private final IHomeView view;

    public HomePresenter(IHomeView view) {
        this.view = view;
        mGetHomeUserDataAPI = new GetHomeUserDataAPI(this);
        mGetHomeDataAPI = new GetHomeDataAPI(this);
    }

    public void initUser(int userId){

        mGetHomeUserDataAPI.getUser(userId);

    }

    public void onGetUserSuccess(HomeUser user){

        view.getUser(user);

    }

    public void onGetUserFailure(String message){

        view.showConnectionError();

    }

    public void getHomeData(int teamId){

        mGetHomeDataAPI.getData(teamId);

    }

    public void onGetHomeDataSuccess(HomeData body) {

        view.presentData(body);

    }

    public void onGetDataFailure() {

        view.showConnectionError();

    }
}
