package com.example.mateusz.fantasy.authentication.register.view;

public interface IRegisterView {

    static final String TAG = IRegisterView.class.getName();

    void onSignUpSuccess(String email);

    void onEmailError(String error);

    void onFirstNameError(String error);

    void onLastNameError(String error);

    void onTeamNameError(String error);

    void onPasswordError(String error);

    void onPasswordRepeatError(String error);

    void onGeneralError(String error);

    void clearErrors();

    void showProgress(boolean show);
}
