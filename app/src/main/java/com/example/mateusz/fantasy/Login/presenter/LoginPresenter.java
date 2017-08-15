package com.example.mateusz.fantasy.Login.presenter;

import android.content.res.Resources;
import android.text.TextUtils;

import com.example.mateusz.fantasy.Login.model.ApiInteractor;
import com.example.mateusz.fantasy.Login.model.User;
import com.example.mateusz.fantasy.Login.view.ILoginView;
import com.example.mateusz.fantasy.R;



/**
 * Created by Mateusz on 15.08.2017.
 */

public class LoginPresenter {

    private ILoginView view;
    private final ApiInteractor mApiinteractor;

    public LoginPresenter() {
        mApiinteractor = new ApiInteractor(this);
    }

    public void login (String email, String password){

        if (!validateFields(email, password)) {
            return;
        }

        view.clearErrors();
        view.showProgress(true);

       mApiinteractor.login(email,password);

    }

    public void loginSuccessfull(){
        view.showProgress(false);
        view.onLoginSuccess();
    }

    public void loginUnsuccessfull(String error){
        view.showProgress(false);
        view.onGeneralError(error);
    }

    public void onViewAttached(ILoginView view){
        this.view = view;
    }

    public void onViewDetached(){
        view = null;
    }

    /**
     * Check if user date is correct
     *
     * @param email    user email
     * @param password user password
     * @return true if AOK
     */
    private boolean validateFields(String email, String password) {
        boolean check = true;

        if (TextUtils.isEmpty(email)) {
            view.onEmailError("pusty email");
            check = false;
        } else {
            view.onEmailError("");
        }

        if (TextUtils.isEmpty(password)) {
           view.onPasswordError("puste hasło");
            check = false;
        } else {
            view.onPasswordError("");
        }

        return check;
    }
}
