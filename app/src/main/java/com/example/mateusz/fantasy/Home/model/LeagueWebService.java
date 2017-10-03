package com.example.mateusz.fantasy.Home.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LeagueWebService {

    @FormUrlEncoded
    @POST("getuserleagues.php")
    Call<LeagueJsonResponse> getLeagues(@Field("user_id") int userId, @Field("user_points") int totalPoints);

    @FormUrlEncoded
    @POST("getleaguedetail.php")
    Call<LeagueDetailJsonResponse> getUsersRank(@Field("league_id") int leagueId);
}
