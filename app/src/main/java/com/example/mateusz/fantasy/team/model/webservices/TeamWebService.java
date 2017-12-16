package com.example.mateusz.fantasy.team.model.webservices;



import com.example.mateusz.fantasy.team.model.repo.PlayerJSONResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TeamWebService {

    @POST("get-all-players.php")
    Call<PlayerJSONResponse> getAllPlayers();
}
