package com.example.mateusz.fantasy.authentication.login.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.mateusz.fantasy.authentication.login.presenter.LoginPresenter;
import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.authentication.register.view.RegisterActivity;
import com.example.mateusz.fantasy.home.view.HomeActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mateusz.fantasy.authentication.register.view.RegisterActivity.PUT_EMAIL_EXTRA;
import static com.example.mateusz.fantasy.utils.NetworkUtils.showConnectionErrorToast;

public class LoginActivity extends Activity implements ILoginView {

    public static final String USER_ID_EXTRA = "user_id";
    public static final String TOTAL_POINTS_EXTRA= "total_points";
    public static final String PREFS_NAME = "com.example.mateusz.fantasy";
    public static final String TEAM_ID_EXTRA ="team_id" ;
    public static final String BUDGET_EXTRA ="budget" ;

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

    private LoginPresenter sPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        setKeyboardListener();

        if (sPresenter == null) sPresenter = new LoginPresenter();

        Intent intent = getIntent();
        String email = intent.getStringExtra(PUT_EMAIL_EXTRA);
        if (email != null) mEtEmail.setText(email);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        int id = sharedPreferences.getInt(USER_ID_EXTRA, 0);

        if (id > 0) {
            intent = new Intent(this, HomeActivity.class);
            intent.putExtra(USER_ID_EXTRA,id);
            startActivity(intent);
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
    public void onBackPressed() {

    }

    @OnClick(R.id.btn_logIn)
    public void login() {
        String email = mEtEmail.getText().toString();
        String password = mEtPassword.getText().toString();
        getPresenter().login(email, password);
    }

    @Override
    public void onLoginSuccess(int userId, int totalPoints, int teamId, float budget) {

        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(USER_ID_EXTRA, userId);
        editor.putInt(TOTAL_POINTS_EXTRA,totalPoints);
        editor.putInt(TEAM_ID_EXTRA,teamId);
        editor.putFloat(BUDGET_EXTRA,budget);
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

        if (show) {
            mPbLoadingIndicator.setVisibility(View.VISIBLE);
        } else {
            mPbLoadingIndicator.setVisibility(View.INVISIBLE);
        }

    }

    @OnClick(R.id.tv_register)
    public void register() {

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    private void setKeyboardListener(){

        mEtPassword.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    login();
                    return true;
                }
                return false;
            }

        });

    }

    @Override
    public void onConnectionError() {
        showConnectionErrorToast(this);

    }

    public LoginPresenter getPresenter() {
        return sPresenter;
    }

}
