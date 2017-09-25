package com.example.mateusz.fantasy.Authentication.Register.view;

/**
 * Created by Mateusz on 19.08.2017.
 */

public interface IRegisterView {

    static final String TAG = IRegisterView.class.getName();

    void onSignUpSuccess(String email);

    void onEmailError(String error);

    void onFirstNameError(String error);

    void onLastNameError(String error);

    void onPasswordError(String error);

    void onPasswordRepeatError(String error);

    void onGeneralError(String error);

    void clearErrors();

    void showProgress(boolean show);
}
