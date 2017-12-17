package com.example.mateusz.fantasy.authentication.register.model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterWebService {

    @FormUrlEncoded
    @POST("registerUser.php")
    Call<Response> registerUser(@Field("email") String email,
                                @Field("password") String password,
                                @Field("firstName") String firstName,
                                @Field("lastName") String lastName,
                                @Field("teamName") String teamName);
}
