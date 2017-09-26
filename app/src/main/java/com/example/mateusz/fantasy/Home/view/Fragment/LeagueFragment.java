package com.example.mateusz.fantasy.Home.view.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;


import com.example.mateusz.fantasy.Home.model.League;
import com.example.mateusz.fantasy.Home.presenter.LeaguePresenter;
import com.example.mateusz.fantasy.Home.presenter.RVLeagueAdapter;
import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.Utils.CreateLeagueDialog;
import com.example.mateusz.fantasy.Utils.JoinLeagueDialog;


public class LeagueFragment extends Fragment implements JoinLeagueDialog.LeagueDialogListener, CreateLeagueDialog.CreateLeagueDialogListener {

    private Button mBtnCreateLeague;
    private Button mBtnJoinLeague;

    private LeaguePresenter mLeaguePresenter;

    /**
     * Constructor
     */
    public LeagueFragment() {

        if (mLeaguePresenter == null) {
            mLeaguePresenter = new LeaguePresenter();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_league, container, false);

        initButtons(view);
        initRecyclerView(view);

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


    private void initButtons(View view) {

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

        RecyclerView mRecyclerView;
        mRecyclerView = view.findViewById(R.id.rv_leagues);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(linearLayoutManager);

        RVLeagueAdapter rvAdapter = new RVLeagueAdapter(League.initializeData(), getContext());
        mRecyclerView.setAdapter(rvAdapter);

    }

}
