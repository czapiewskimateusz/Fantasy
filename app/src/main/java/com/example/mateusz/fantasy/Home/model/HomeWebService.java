package com.example.mateusz.fantasy.Home.model;

import com.example.mateusz.fantasy.Authentication.Login.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Ready4s on 03.10.2017.
 */

public interface HomeWebService {

    @FormUrlEncoded
    @POST("getUserById.php")
    Call<User> getUser(@Field("user_id") int userId);
}
