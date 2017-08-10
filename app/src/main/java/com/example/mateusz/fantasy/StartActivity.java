package com.example.mateusz.fantasy;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mateusz.fantasy.Model.User;
import com.example.mateusz.fantasy.Utilities.GetUserLoader;
import com.example.mateusz.fantasy.Utilities.NetworkUtils;
import com.example.mateusz.fantasy.Utilities.UserJsonUtils;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends Activity implements LoaderManager.LoaderCallbacks<String> {

    public static final String GET_USER_QUERY_EXTRA = "getUserQuery";
    private static final int FANTASY_SEARCH_LOADER = 22;

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

    private User loggedUser;
    String getEmail;
    String getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_logIn)
    public void login(View view) {

        getEmail = mEtEmail.getText().toString();
        getPassword = mEtPassword.getText().toString();

        if (!validateFields(getEmail, getPassword)) {
            return;
        }

        URL getUserUrl = NetworkUtils.buildUrl(getEmail);

        Bundle queryBundle = new Bundle();
        queryBundle.putString(GET_USER_QUERY_EXTRA, getUserUrl.toString());

        LoaderManager loaderManager = getLoaderManager();
        Loader<String> fantasySearchLoader = loaderManager.getLoader(FANTASY_SEARCH_LOADER);

        if (fantasySearchLoader == null) {
            loaderManager.initLoader(FANTASY_SEARCH_LOADER, queryBundle, this);
        } else {
            loaderManager.restartLoader(FANTASY_SEARCH_LOADER, queryBundle, this);
        }

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
            mTvErrorEmail.setText(R.string.error_empty_email);
            check = false;
        } else {
            mTvErrorEmail.setText("");
        }

        if (TextUtils.isEmpty(password)) {
            mTvErrorPassword.setText(R.string.error_password_empty);
            check = false;
        } else {
            mTvErrorPassword.setText("");
        }

        return check;
    }


    @Override
    public Loader<String> onCreateLoader(int i, final Bundle args) {

        return new GetUserLoader(this, args, mPbLoadingIndicator, mTvError);

    }

    @Override
    public void onLoadFinished(Loader<String> loader, String jsonResponse) {
        mPbLoadingIndicator.setVisibility(View.INVISIBLE);
        mTvError.setVisibility(View.VISIBLE);
        if (null == jsonResponse) {
            mTvError.setText("ERROR");
        } else {

            loggedUser = UserJsonUtils.getUserFromJson(jsonResponse);
            if (loggedUser == null) {
                mTvError.setText("Użytkownik nie istnieje");

            } else if (loggedUser.getPassword().equals(getPassword)) {
                mTvError.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            } else {
                mTvError.setText("ZŁE HASŁO");
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
