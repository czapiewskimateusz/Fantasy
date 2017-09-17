package com.example.mateusz.fantasy.Login.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.mateusz.fantasy.Login.presenter.LoginPresenter;
import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.Register.view.RegisterActivity;
import com.example.mateusz.fantasy.Home.view.HomeActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mateusz.fantasy.Register.view.RegisterActivity.PUT_EMAIL_EXTRA;

public class LoginActivity extends Activity implements ILoginView {

    public static final String USER_ID_EXTRA = "user_id";
    public static final String PREFS_NAME = "com.example.mateusz.fantasy";

    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.tv_error)
    TextView mTvError;
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mPbLoadingIndicator;
    @BindView(R.id.tv_errorEmail)
    TextView mTvErrorEmail;
    @BindView(R.id.tv_errorPassword)
    TextView mTvErrorPassword;

    private static LoginPresenter sPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (sPresenter == null) {
            sPresenter = new LoginPresenter();
        }

        Intent intent = getIntent();
        String email = intent.getStringExtra(PUT_EMAIL_EXTRA);
        if (email!=null){
            mEtEmail.setText(email);
        }

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        int id = sharedPreferences.getInt(USER_ID_EXTRA,0);

        if (id>0){
            intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onViewAttached(this,this);
    }

    @Override
    protected void onPause() {

        super.onPause();
        getPresenter().onViewDetached();

    }

    @OnClick(R.id.btn_logIn)
    public void login(View view) {

        String email = mEtEmail.getText().toString();
        String password = mEtPassword.getText().toString();
        getPresenter().login(email, password);
    }

    @Override
    public void onLoginSuccess(int userId) {

        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(USER_ID_EXTRA, userId);
        editor.apply();

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }

    @Override
    public void onEmailError(String error) {
        mTvErrorEmail.setText(error);
    }

    @Override
    public void onPasswordError(String error) {
        mTvErrorPassword.setText(error);
    }

    @Override
    public void onGeneralError(String error) {
        mTvError.setText(error);
    }

    @Override
    public void clearErrors() {
        mTvErrorEmail.setText("");
        mTvErrorPassword.setText("");
        mTvError.setText("");
    }

    @Override
    public void showProgress(boolean show) {

        if (show){
            mPbLoadingIndicator.setVisibility(View.VISIBLE);
        } else {
            mPbLoadingIndicator.setVisibility(View.INVISIBLE);
        }

    }

    @OnClick (R.id.tv_register)
    public void register(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public LoginPresenter getPresenter() {
        return sPresenter;
    }

}
