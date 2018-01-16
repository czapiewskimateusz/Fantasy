package com.example.mateusz.fantasy.authentication.register.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.authentication.register.model.RegisterApiInteractor;
import com.example.mateusz.fantasy.authentication.register.view.IRegisterView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.BUDGET_EXTRA;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.PREFS_NAME;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.TEAM_ID_EXTRA;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.TOTAL_POINTS_EXTRA;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.USER_ID_EXTRA;

public class RegisterPresenter {

    private String mEmail;

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

    public void onRegisterSuccess(int userId, int teamID) {
        view.showProgress(false);
        saveUserData(userId, teamID);
        view.onSignUpSuccess();
    }

    private void saveUserData(int userId, int teamId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(USER_ID_EXTRA, userId);
        editor.putInt(TOTAL_POINTS_EXTRA, 0);
        editor.putInt(TEAM_ID_EXTRA, teamId);
        editor.putFloat(BUDGET_EXTRA, 75);
        editor.apply();
    }

    public void onRegisterFailure() {
        view.showProgress(false);
        view.onGeneralError(context.getString(R.string.user_already_exists_error));
    }

    public void register(String email, String firstname, String lastname, String teamName, String password, String passwordRepeat) {
        view.clearErrors();
        if (!validateFields(email, firstname, lastname, teamName, password, passwordRepeat)) return;
        this.mEmail = email;
        view.showProgress(true);
        registerApiInteractor.register(email, firstname, lastname, teamName, password);
    }

    /**
     * Function to vaildate data provided by the User
     *
     * @param email          user email
     * @param firstname      user firstname
     * @param lastname       user lastname
     * @param password       user password
     * @param passwordRepeat user passwordRepeat
     * @return true if AOK
     */
    private boolean validateFields(String email, String firstname, String lastname, String teamName, String password, String passwordRepeat) {
        boolean check = true;

        if (TextUtils.isEmpty(email)) {
            view.onEmailError(context.getString(R.string.error_empty_email));
            check = false;
        } else if (email.length() > 50) {
            view.onEmailError(context.getString(R.string.email_length_error));
            check = false;
        } else {
            view.onEmailError("");
        }

        if (TextUtils.isEmpty(firstname)) {
            view.onFirstNameError(context.getString(R.string.firstname_empty_error));
            check = false;
        } else if (firstname.length() > 50) {
            view.onFirstNameError(context.getString(R.string.firstname_length_error));
            check = false;
        } else {
            view.onFirstNameError("");
        }

        if (TextUtils.isEmpty(lastname)) {
            view.onLastNameError(context.getString(R.string.lastname_empty_error));
            check = false;
        } else if (lastname.length() > 50) {
            view.onLastNameError(context.getString(R.string.lastname_length_error));
            check = false;
        } else {
            view.onLastNameError("");
        }

        if (TextUtils.isEmpty(teamName)) {
            view.onTeamNameError(context.getString(R.string.teamName_empty_error));
            check = false;
        } else if (lastname.length() > 50) {
            view.onTeamNameError(context.getString(R.string.teamName_length_error));
            check = false;
        } else {
            view.onTeamNameError("");
        }

        if (TextUtils.isEmpty(password)) {
            view.onPasswordError(context.getString(R.string.error_password_empty));
            check = false;
        } else if (password.length() < 6) {
            view.onPasswordError(context.getString(R.string.password_length_error));
            check = false;
        } else {
            view.onPasswordError("");
        }

        if (!password.equals(passwordRepeat)) {
            view.onPasswordError(context.getString(R.string.passwords_do_not_match));
            view.onPasswordRepeatError(context.getString(R.string.passwords_do_not_match));
            check = false;
        } else if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(passwordRepeat)) {
            view.onPasswordError("");
            view.onPasswordRepeatError("");
        }

        if (TextUtils.isEmpty(passwordRepeat)) {
            view.onPasswordRepeatError(context.getString(R.string.error_password_empty));
            check = false;
        } else if (passwordRepeat.length() < 6) {
            view.onPasswordRepeatError(context.getString(R.string.password_length_error));
            check = false;
        } else {
            view.onPasswordRepeatError("");
        }
        return check;
    }

    public void onConnectionError() {
        view.onGeneralError(context.getString(R.string.connection_error));
    }
}
