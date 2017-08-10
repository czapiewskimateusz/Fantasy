package com.example.mateusz.fantasy.Utilities;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.mateusz.fantasy.StartActivity.GET_USER_QUERY_EXTRA;

/**
 * Created by Mateusz on 07.08.2017.
 */

public class GetUserLoader extends AsyncTaskLoader<String> {
    private String mGetUserJson;
    private Bundle args;
    private ProgressBar mPbLoadingIndicator;
    private TextView mTvError;

    public GetUserLoader(Context context, Bundle args, ProgressBar mPbLoadingIndicator, TextView mTvError) {
        super(context);
        this.args = args;
        this.mPbLoadingIndicator = mPbLoadingIndicator;
        this.mTvError = mTvError;
    }

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


}
