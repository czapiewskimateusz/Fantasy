package com.example.mateusz.fantasy.team.model.webservices;


import com.example.mateusz.fantasy.authentication.register.model.Response;
import com.example.mateusz.fantasy.team.model.repo.PlayerJSONResponse;
import com.example.mateusz.fantasy.team.presenter.adapters.RVAllPlayerAdapter;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TeamWebService {

    @POST("get-all-players.php")
    Call<PlayerJSONResponse> getAllPlayers();

    @FormUrlEncoded
    @POST("get-users-team.php")
    Call<PlayerJSONResponse> getUsersTeam(@Field("team_id") int teamId);

    @FormUrlEncoded
    @POST("update-team.php")
    Call<Response> updateTeam(@Field("team_id") int teamId,
                              @Field("user_id") int userId,
                              @Field("budget") float budget,
                              @Field("gk") int gk,
                              @Field("d1") int d1,
                              @Field("d2") int d2,
                              @Field("d3") int d3,
                              @Field("d4") int d4,
                              @Field("m1") int m1,
                              @Field("m2") int m2,
                              @Field("m3") int m3,
                              @Field("m4") int m4,
                              @Field("a1") int a1,
                              @Field("a2") int a2);
}
