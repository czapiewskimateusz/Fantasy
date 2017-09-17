package com.example.mateusz.fantasy.Login.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.mateusz.fantasy.Login.model.LoginApiInteractor;
import com.example.mateusz.fantasy.Login.model.User;
import com.example.mateusz.fantasy.Login.view.ILoginView;
import com.example.mateusz.fantasy.R;

public class LoginPresenter {

    /**
     * Constant fields
     */
    public static final String ERROR_TAG_INCORRECT_PASSWORD = "incorrect_password";
    public static final String ERROR_TAG_USER_DOESNT_EXIST = "user_doesnt_exist";

    /**
     * Dependencies
     */
    private ILoginView view;
    private Context context;
    private final LoginApiInteractor mApiinteractor;

    /**
     * Constructor
     */
    public LoginPresenter() {

        mApiinteractor = new LoginApiInteractor(this);

    }

    public void onViewAttached(ILoginView view, Context context) {

        this.view = view;
        this.context = context;

    }

    public void onViewDetached() {

        view = null;
        context = null;

    }

    /**
     * Retrieves data from view and passes it to LoginApiInteractor
     *
     * @param email    users email
     * @param password users password
     */
    public void login(String email, String password) {

        if (!validateFields(email, password)) {
            return;
        }

        view.clearErrors();
        view.showProgress(true);

        mApiinteractor.login(email, password);

    }


    public void onLoginSuccessful(int userId) {

        view.showProgress(false);
        view.onLoginSuccess(userId);

    }

    public void onLoginUnsuccessful(String error) {

        view.showProgress(false);
        if (error.equals(ERROR_TAG_INCORRECT_PASSWORD)) {
            view.onGeneralError(context.getString(R.string.password_error));
        }
        if (error.equals(ERROR_TAG_USER_DOESNT_EXIST)) {

            view.onGeneralError(context.getString(R.string.user_exists_error));

        }

    }


    /**
     * Check if user date is correct
     *
     * @param email    users email
     * @param password users password
     * @return true if AOK, false if incorrect data
     */
    private boolean validateFields(String email, String password) {

        boolean check = true;

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

        return check;

    }

}
