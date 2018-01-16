package com.example.mateusz.fantasy.home.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.home.model.API.EditUserAPI;
import com.example.mateusz.fantasy.home.model.repo.ServerResponse;
import com.example.mateusz.fantasy.home.view.HomeActivity;
import com.example.mateusz.fantasy.home.view.UserDetailView;
import com.example.mateusz.fantasy.utils.NetworkUtils;

import static com.example.mateusz.fantasy.home.model.repo.ServerResponse.MESSAGE_SUCCESS;

public class UserDetailPresenter {

    private UserDetailView view;
    private Context context;
    private EditUserAPI editUserAPI;

    public UserDetailPresenter(UserDetailView view, Context context) {
        this.view = view;
        this.context = context;
        editUserAPI = new EditUserAPI(this);
    }

    public void editUser(String userEmail, String firstName, String lastName, String newEmail, String newPassword, String newPasswordRepeat) {
        view.clearErrors();
        if (validateFields(newEmail,firstName,lastName, newPassword, newPasswordRepeat))
            view.showProgress(true);
           editUserAPI.editUser(userEmail,newEmail,firstName,lastName,newPassword);
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
    private boolean validateFields(String email, String firstname, String lastname, String password, String passwordRepeat) {

        boolean check = true;

        if (TextUtils.isEmpty(email)) {
            view.onEmailError(context.getString(R.string.error_empty_email));
            check = false;
        } else if (email.length() > 50) {
            view.onEmailError(context.getString(R.string.email_length_error));
            check = false;
        } else
            view.onEmailError("");

        if (TextUtils.isEmpty(firstname)) {
            view.onFirstNameError(context.getString(R.string.firstname_empty_error));
            check = false;
        } else if (firstname.length() > 50) {
            view.onFirstNameError(context.getString(R.string.firstname_length_error));
            check = false;
        } else
            view.onFirstNameError("");

        if (TextUtils.isEmpty(lastname)) {
            view.onLastNameError(context.getString(R.string.lastname_empty_error));
            check = false;
        } else if (lastname.length() > 50) {
            view.onLastNameError(context.getString(R.string.lastname_length_error));
            check = false;
        } else
            view.onLastNameError("");

        if (TextUtils.isEmpty(password)) {
            view.onPasswordError(context.getString(R.string.error_password_empty));
            check = false;
        } else if (password.length() < 6) {
            view.onPasswordError(context.getString(R.string.password_length_error));
            check = false;
        } else
            view.onPasswordError("");

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
        } else
            view.onPasswordRepeatError("");

        return check;
    }

    public void onResponse(ServerResponse body) {
        view.showProgress(false);
        if (body.getMessage().equals(MESSAGE_SUCCESS)){
            Toast.makeText(context,context.getText(R.string.edit_success),Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, HomeActivity.class));
        }
        else view.onEditError();
    }

    public void onConnectionError() {
        view.showProgress(false);
        view.onConnectionError();
    }
}
