package com.example.mateusz.fantasy.leagues.model.API;

import com.example.mateusz.fantasy.authentication.register.model.Response;
import com.example.mateusz.fantasy.leagues.model.repo.LeagueDetailJsonResponse;
import com.example.mateusz.fantasy.leagues.model.repo.LeagueJsonResponse;

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

    @FormUrlEncoded
    @POST("joinLeague.php")
    Call<Response> joinLeague(@Field("user_id") int userId,@Field("league_code") String leagueCode);

    @FormUrlEncoded
    @POST("createLeague.php")
    Call<Response> createLeague(@Field("league_name") String leagueName, @Field("user_id") int userId);

}
