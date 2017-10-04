package com.example.mateusz.fantasy.home.model.webService;

import com.example.mateusz.fantasy.authentication.login.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface HomeWebService {

    @FormUrlEncoded
    @POST("getUserById.php")
    Call<User> getUser(@Field("user_id") int userId);
}