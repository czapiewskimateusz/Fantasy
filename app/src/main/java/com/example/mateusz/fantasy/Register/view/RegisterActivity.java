package com.example.mateusz.fantasy.Register.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mateusz.fantasy.Login.view.LoginActivity;
import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.Register.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends Activity implements IRegisterView {

    public static final String PUT_EMAIL_EXTRA = "email_extra";

    //EditText
    @BindView(R.id.et_email_register)
    public EditText mEtEmail;
    @BindView(R.id.et_firstname)
    public EditText mEtFirstName;
    @BindView(R.id.et_lastname)
    public EditText mEtLastName;
    @BindView(R.id.et_password_register)
    public EditText mEtPassword;
    @BindView(R.id.et_password_repeat)
    public EditText mEtPasswordRepeat;

    //ErrorTextViews
    @BindView(R.id.tv_email_register_error)
    public TextView mTvEmailError;
    @BindView(R.id.tv_firstname_error)
    public TextView mTvFirstnameError;
    @BindView(R.id.tv_lastname_error)
    public TextView mTvLastnameError;
    @BindView(R.id.tv_password_register_error)
    public TextView mTvPasswordError;
    @BindView(R.id.tv_password_repeat_error)
    public TextView mTvPasswordRepeatError;
    @BindView(R.id.tv_error_register)
    public TextView mTvGeneralRegisterError;

    //Loading indicator
    @BindView(R.id.pb_register_loading_indicator)
    ProgressBar mPbLoadingIndicator;

    private static RegisterPresenter sPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (sPresenter == null) {
            sPresenter = new RegisterPresenter();
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
        getPresenter().onViewAttached(this, this);

    }

    @Override
    protected void onPause() {

        super.onPause();
        getPresenter().onViewDetached();

    }

    @Override
    public void onSignUpSuccess(String email) {
        Toast.makeText(this,getString(R.string.user_successfully_created),Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(PUT_EMAIL_EXTRA,email);
        startActivity(intent);
    }

    @Override
    public void onEmailError(String error) {
        mTvEmailError.setText(error);
    }

    @Override
    public void onFirstNameError(String error) {
        mTvFirstnameError.setText(error);
    }

    @Override
    public void onLastNameError(String error) {
        mTvLastnameError.setText(error);
    }

    @Override
    public void onPasswordError(String error) {
        mTvPasswordError.setText(error);
    }

    @Override
    public void onPasswordRepeatError(String error) {
        mTvPasswordRepeatError.setText(error);
    }

    @Override
    public void onGeneralError(String error) {
        mTvGeneralRegisterError.setText(error);
    }

    @Override
    public void clearErrors() {
        mTvEmailError.setText("");
        mTvFirstnameError.setText("");
        mTvLastnameError.setText("");
        mTvPasswordError.setText("");
        mTvPasswordRepeatError.setText("");
        mTvGeneralRegisterError.setText("");
    }

    @Override
    public void showProgress(boolean show) {

        if (show){
            mPbLoadingIndicator.setVisibility(View.VISIBLE);
        } else {
            mPbLoadingIndicator.setVisibility(View.INVISIBLE);
        }

    }

    @OnClick(R.id.btn_register)
    public void register() {

        String email = mEtEmail.getText().toString();
        String firstname = mEtFirstName.getText().toString();
        String lastname = mEtLastName.getText().toString();
        String password = mEtPassword.getText().toString();
        String passwordRepeat = mEtPasswordRepeat.getText().toString();

        getPresenter().register(email,firstname,lastname,password,passwordRepeat);

    }

    public RegisterPresenter getPresenter() {
        return sPresenter;
    }
}
