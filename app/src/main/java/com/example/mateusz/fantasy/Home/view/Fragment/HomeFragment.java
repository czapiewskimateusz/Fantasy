package com.example.mateusz.fantasy.Home.view.Fragment;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mateusz.fantasy.Authentication.Login.view.LoginActivity;
import com.example.mateusz.fantasy.Home.presenter.HomePresenter;
import com.example.mateusz.fantasy.R;


import static android.content.Context.MODE_PRIVATE;
import static com.example.mateusz.fantasy.Authentication.Login.view.LoginActivity.PREFS_NAME;
import static com.example.mateusz.fantasy.Authentication.Login.view.LoginActivity.TOTAL_POINTS_EXTRA;
import static com.example.mateusz.fantasy.Authentication.Login.view.LoginActivity.USER_ID_EXTRA;


public class HomeFragment extends Fragment{

    private HomePresenter mHomePresenter;

    private Button mBtnLogOut;
    private int mUserId;

    public HomeFragment() {
        // Required empty public constructor
        if (mHomePresenter == null) {
            mHomePresenter = new HomePresenter();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mBtnLogOut = view.findViewById(R.id.btn_logout);
        initButton();

        mUserId = getActivity().getIntent().getIntExtra(USER_ID_EXTRA,0);
        return view;
    }

    private void initButton() {

        mBtnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                editor.putInt(USER_ID_EXTRA, 0);
                editor.putInt(TOTAL_POINTS_EXTRA,0);
                editor.apply();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });

    }

    private void initUser(){
        mHomePresenter.initUser(mUserId);
    }
}
