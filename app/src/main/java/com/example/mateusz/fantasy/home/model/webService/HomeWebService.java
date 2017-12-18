package com.example.mateusz.fantasy.home.model.webService;

import com.example.mateusz.fantasy.home.model.repo.HomeData;
import com.example.mateusz.fantasy.home.model.repo.HomeUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface HomeWebService {

    @FormUrlEncoded
    @POST("getUserById.php")
    Call<HomeUser> getUser(@Field("user_id") int userId);

    @FormUrlEncoded
    @POST("getHome.php")
    Call<HomeData> getData(@Field("user_id") int userId);

}
