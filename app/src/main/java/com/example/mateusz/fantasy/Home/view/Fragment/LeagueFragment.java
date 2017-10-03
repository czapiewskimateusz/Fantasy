package com.example.mateusz.fantasy.Home.view.Fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ProgressBar;


import com.example.mateusz.fantasy.Authentication.Login.view.LoginActivity;
import com.example.mateusz.fantasy.Home.model.League;
import com.example.mateusz.fantasy.Home.presenter.LeaguePresenter;
import com.example.mateusz.fantasy.Home.presenter.RVLeagueAdapter;
import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.Utils.CreateLeagueDialog;
import com.example.mateusz.fantasy.Utils.JoinLeagueDialog;

import java.util.List;

import static com.example.mateusz.fantasy.Authentication.Login.view.LoginActivity.TOTAL_POINTS_EXTRA;
import static com.example.mateusz.fantasy.Authentication.Login.view.LoginActivity.USER_ID_EXTRA;


public class LeagueFragment extends Fragment implements ILeagueView, JoinLeagueDialog.LeagueDialogListener, CreateLeagueDialog.CreateLeagueDialogListener {

    private Button mBtnCreateLeague;
    private Button mBtnJoinLeague;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private LeaguePresenter mLeaguePresenter;

    /**
     * Constructor
     */
    public LeagueFragment() {

        if (mLeaguePresenter == null) {
            mLeaguePresenter = new LeaguePresenter(this);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_league, container, false);

        initButtons(view);
        initRecyclerView(view);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginActivity.PREFS_NAME,0);
        int id = sharedPreferences.getInt(USER_ID_EXTRA,0);
        int totalPoints = sharedPreferences.getInt(TOTAL_POINTS_EXTRA,0);

        mLeaguePresenter.getUserLeagues(id,totalPoints);

        return view;
    }

    @Override
    public void onDialogPositiveClick(JoinLeagueDialog dialog) {

        mLeaguePresenter.joinLeague(dialog.getCode());

    }

    @Override
    public void onDialogPositiveClick(CreateLeagueDialog dialog) {

        mLeaguePresenter.createLeague(dialog.getName());

    }

    @Override
    public void showProgress(boolean show) {

        if (show){
            mProgressBar.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void presentLeagues(List<League> leagues) {

        RVLeagueAdapter rvAdapter = new RVLeagueAdapter(leagues, getContext());
        mRecyclerView.setAdapter(rvAdapter);

    }

    private void initButtons(View view) {

        mProgressBar = view.findViewById(R.id.pB_league_fragment);

        mBtnCreateLeague = view.findViewById(R.id.btn_create_league);
        mBtnCreateLeague.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                view.startAnimation(buttonClick);

                CreateLeagueDialog createLeagueDialog = new CreateLeagueDialog();
                createLeagueDialog.setTargetFragment(LeagueFragment.this, 0);
                createLeagueDialog.show(getFragmentManager(), "league_join_dialog");

            }
        });

        mBtnJoinLeague = view.findViewById(R.id.btn_join_league);
        mBtnJoinLeague.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                view.startAnimation(buttonClick);

                JoinLeagueDialog leagueDialog = new JoinLeagueDialog();
                leagueDialog.setTargetFragment(LeagueFragment.this, 0);
                leagueDialog.show(getFragmentManager(), "league_join_dialog");

            }
        });

    }


    /**
     * Prepare RecyclerView for action
     *
     * @param view parent view
     */
    private void initRecyclerView(View view) {

        mRecyclerView = view.findViewById(R.id.rv_leagues);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(linearLayoutManager);

    }

}
