package com.example.mateusz.fantasy.Register.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.Register.model.RegisterApiInteractor;
import com.example.mateusz.fantasy.Register.view.IRegisterView;

/**
 * Created by Mateusz on 19.08.2017.
 */

public class RegisterPresenter {

    /**
     * Dependencies
     */
    private IRegisterView view;
    private Context context;
    private final RegisterApiInteractor registerApiInteractor;

    /**
     * Constructor
     */
    public RegisterPresenter() {

        this.registerApiInteractor = new RegisterApiInteractor(this);

    }

    public void onViewAttached(IRegisterView view, Context context) {

        this.view = view;
        this.context = context;

    }

    public void onViewDetached() {

        view = null;
        context = null;

    }

    public void onRegisterSuccess(){
        view.showProgress(false);
        view.onGeneralError("Przyszło gówno");
    }

    public void onRegisterFailure(){
        view.showProgress(false);
    }

    public void register(String email, String firstname, String lastname, String password, String passwordRepeat) {

        if (!validateFields(email, firstname, lastname, password, passwordRepeat)) {
            return;
        }

        view.clearErrors();
        view.showProgress(true);

        registerApiInteractor.register(email,firstname,lastname,password);

    }


    private boolean validateFields(String email, String firstname, String lastname, String password, String passwordRepeat) {

        boolean check = true;

        if (!password.equals(passwordRepeat)){
            view.onPasswordError(context.getString(R.string.passwords_do_not_match_error));
            check = false;
        } else{
            view.onPasswordError("");
        }

        if (TextUtils.isEmpty(email)) {
            view.onEmailError(context.getString(R.string.error_empty_email));
            check = false;
        } else {
            view.onEmailError("");
        }

        if (TextUtils.isEmpty(password)) {
            view.onPasswordError(context.getString(R.string.error_password_empty));
            check = false;
        } else {
            view.onPasswordError("");
        }

        if (TextUtils.isEmpty(passwordRepeat)) {
            view.onPasswordError(context.getString(R.string.error_password_empty));
            check = false;
        } else {
            view.onPasswordError("");
        }

        if (TextUtils.isEmpty(firstname)) {
            view.onFirstNameError(context.getString(R.string.firstname_empty_error));
            check = false;
        } else {
            view.onFirstNameError("");
        }

        if (TextUtils.isEmpty(lastname)) {
            view.onLastNameError(context.getString(R.string.lastname_empty_error));
            check = false;
        } else {
            view.onLastNameError("");
        }

        return check;

    }
}
