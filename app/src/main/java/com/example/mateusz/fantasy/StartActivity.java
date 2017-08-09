package com.example.mateusz.fantasy;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mateusz.fantasy.Model.User;
import com.example.mateusz.fantasy.Utilities.NetworkUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class StartActivity extends Activity implements LoaderManager.LoaderCallbacks<String> {

    public static final String GET_USER_QUERY_EXTRA = "getUserQuery";

    private EditText mEtEmail;
    private EditText mEtPassword;

    private TextView mTvError;

    private ProgressBar mPbLoadingIndicator;

    public static User loggedUser;

    private static final int FANTASY_SEARCH_LOADER = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setViewWidgets();

    }

    private void setViewWidgets() {
        mEtEmail = findViewById(R.id.et_email);
        mEtPassword = findViewById(R.id.et_password);
        mTvError = findViewById(R.id.tv_error);
        mPbLoadingIndicator = findViewById(R.id.pb_loading_indicator);
    }


    public void login(View view) {
        String getEmail = mEtEmail.getText().toString();
        String getPassword = mEtPassword.getText().toString();


        if (!validateFields(getEmail, getPassword)) {
            return;
        }

        URL getUserUrl = NetworkUtils.buildUrl(getEmail);

        Bundle queryBundle = new Bundle();
        queryBundle.putString(GET_USER_QUERY_EXTRA, getUserUrl.toString());

        LoaderManager loaderManager = getLoaderManager();
        Loader<String> fantasySearchLoader = loaderManager.getLoader(FANTASY_SEARCH_LOADER);

        if (fantasySearchLoader == null) {
            loaderManager.initLoader(FANTASY_SEARCH_LOADER,queryBundle,this);
        } else {
            loaderManager.restartLoader(FANTASY_SEARCH_LOADER,queryBundle,this);
        }
    }

    private boolean validateFields(String email, String password) {
        mTvError.setVisibility(View.VISIBLE);

        if (TextUtils.isEmpty(email)) {
            mTvError.setText(R.string.error_empty_email);
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            mTvError.setText(R.string.error_password_empty);
            return false;
        }
        return true;
    }


    @Override
    public Loader<String> onCreateLoader(int i, final Bundle args) {


        return new AsyncTaskLoader<String>(this) {

            String mGetUserJson;

            @Override
            protected void onStartLoading() {

                if (args == null){
                    return;
                }

                mPbLoadingIndicator.setVisibility(View.VISIBLE);
                mTvError.setVisibility(View.INVISIBLE);

                if (mGetUserJson !=null){
                    deliverResult(mGetUserJson);
                } else {
                    forceLoad();
                }
            }

            @Override
            public String loadInBackground() {
                String getUserQueryUrlString = args.getString(GET_USER_QUERY_EXTRA);

                if (getUserQueryUrlString == null || TextUtils.isEmpty(getUserQueryUrlString)){
                    return null;
                }

                try {
                    URL getUserURL = new URL(getUserQueryUrlString);
                    String userSearchResult = NetworkUtils.getResponseFromHttpUrl(getUserURL);
                    return userSearchResult;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(String data) {
                mGetUserJson = data;
                super.deliverResult(mGetUserJson);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        mPbLoadingIndicator.setVisibility(View.INVISIBLE);
        mTvError.setVisibility(View.VISIBLE);
        if (null == s){
            mTvError.setText("ERROR");
        } else {
            mTvError.setText(s);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
