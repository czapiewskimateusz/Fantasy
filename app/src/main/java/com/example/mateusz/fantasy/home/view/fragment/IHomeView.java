package com.example.mateusz.fantasy.home.view.fragment;


import com.example.mateusz.fantasy.home.model.repo.HomeData;
import com.example.mateusz.fantasy.home.model.repo.HomeUser;

public interface IHomeView {

    void getUser(HomeUser user);

    void showConnectionError();

    void presentData(HomeData body);
}
