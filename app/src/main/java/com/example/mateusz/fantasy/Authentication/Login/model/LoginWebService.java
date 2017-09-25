package com.example.mateusz.fantasy.Authentication.Login.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mateusz on 15.08.2017.
 */

public interface LoginWebService  {

    @GET("getuser.php?")
    Call<User> getUser(@Query("email") String email);
}
