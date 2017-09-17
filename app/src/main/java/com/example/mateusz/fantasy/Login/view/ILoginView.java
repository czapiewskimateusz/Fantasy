package com.example.mateusz.fantasy.Login.view;

/**
 * Created by Mateusz on 15.08.2017.
 */

public interface ILoginView {

    static final String TAG = ILoginView.class.getName();

    void onLoginSuccess(int userId);

    void onEmailError(String error);

    void onPasswordError(String error);

    void onGeneralError(String error);

    void clearErrors();

    void showProgress(boolean show);
}
