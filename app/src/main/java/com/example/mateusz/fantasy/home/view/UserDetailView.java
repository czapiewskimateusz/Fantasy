package com.example.mateusz.fantasy.home.view;

/**
 * Created by Mateusz on 29.10.2017.
 */

public interface UserDetailView {

    void onEmailError(String string);

    void onFirstNameError(String string);

    void onLastNameError(String string);

    void onPasswordError(String string);

    void onPasswordRepeatError(String string);

    void clearErrors();
}
