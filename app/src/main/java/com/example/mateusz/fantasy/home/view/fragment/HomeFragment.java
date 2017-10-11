package com.example.mateusz.fantasy.home.view.fragment;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.mateusz.fantasy.authentication.login.view.LoginActivity;
import com.example.mateusz.fantasy.home.presenter.HomePresenter;
import com.example.mateusz.fantasy.R;


import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.PREFS_NAME;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.TOTAL_POINTS_EXTRA;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.USER_ID_EXTRA;


public class HomeFragment extends Fragment implements ParentFragment{


    //UI
    public FrameLayout fragmentContainer;

    //Dependencies
    private HomePresenter mHomePresenter;

    public Button mBtnLogOut;

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

        fragmentContainer = view.findViewById(R.id.home_fragment_container);
        mBtnLogOut = view.findViewById(R.id.btn_logout);
        initButton();

        mUserId = getActivity().getIntent().getIntExtra(USER_ID_EXTRA,0);
        return view;
    }


    /**
     * Called when a fragment will be displayed
     */
    @Override
    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    /**
     * Called when a fragment will be hidden
     */
    @Override
    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }
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
