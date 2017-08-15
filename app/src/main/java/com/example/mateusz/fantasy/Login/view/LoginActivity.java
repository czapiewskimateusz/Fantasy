package com.example.mateusz.fantasy.Login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.mateusz.fantasy.Login.presenter.LoginPresenter;
import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.UI.HomeActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity implements ILoginView {

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onViewAttached(this);
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
    public void onLoginSuccess() {
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

    public LoginPresenter getPresenter() {
        return sPresenter;
    }


//    @Override
//    public Loader<String> onCreateLoader(int i, final Bundle args) {
//
//        return new GetUserLoader(this, args, mPbLoadingIndicator, mTvError);
//
//    }
//
//    @Override
//    public void onLoadFinished(Loader<String> loader, String jsonResponse) {
//        mPbLoadingIndicator.setVisibility(View.INVISIBLE);
//        mTvError.setVisibility(View.VISIBLE);
//        if (null == jsonResponse) {
//            mTvError.setText(R.string.connection_error);
//        } else {
//
//            loggedUser = UserJsonUtils.getUserFromJson(jsonResponse);
//            if (loggedUser == null) {
//                mTvError.setText(R.string.user_exists_error);
//
//            } else if (loggedUser.getPassword().equals(getPassword)) {
//                mTvError.setVisibility(View.INVISIBLE);
//                Intent intent = new Intent(this, HomeActivity.class);
//                startActivity(intent);
//            } else {
//                mTvError.setText(R.string.password_error);
//            }
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<String> loader) {
//
//    }
}
