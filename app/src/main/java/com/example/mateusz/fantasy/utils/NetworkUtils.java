package com.example.mateusz.fantasy.utils;

import android.app.Activity;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.example.mateusz.fantasy.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    public static final String BASE_URL = "http://fantasypl.c0.pl/";

    public static Gson getGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    public static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
    }

    public static void showConnectionErrorToast(Activity activity){
        Toast.makeText(activity, activity.getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
    }

}
