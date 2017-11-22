package com.example.mateusz.fantasy.home.view.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.ProgressBar;



import com.example.mateusz.fantasy.authentication.login.view.LoginActivity;
import com.example.mateusz.fantasy.home.model.repo.League;
import com.example.mateusz.fantasy.home.presenter.LeaguePresenter;
import com.example.mateusz.fantasy.home.presenter.adapters.RVLeagueAdapter;
import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.utils.dialogs.CreateLeagueDialog;
import com.example.mateusz.fantasy.utils.dialogs.JoinLeagueDialog;
import com.github.clans.fab.FloatingActionMenu;

import java.util.List;


import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.TOTAL_POINTS_EXTRA;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.USER_ID_EXTRA;


public class LeagueFragment extends Fragment implements ILeagueView, JoinLeagueDialog.LeagueDialogListener, CreateLeagueDialog.CreateLeagueDialogListener, ParentFragment {

    //UI
    private FloatingActionMenu mFloatingActionMenu;
    private com.github.clans.fab.FloatingActionButton mFABCreateLeague;
    private com.github.clans.fab.FloatingActionButton mFABJoinLeague;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private CoordinatorLayout mFragmentContainer;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public LeaguePresenter mLeaguePresenter;

    private int mUserId;
    private int mTotalPoints;
    RVLeagueAdapter rvAdapter = null;

    /**
     * Constructor
     */
    public LeagueFragment() {
        if (mLeaguePresenter == null) mLeaguePresenter = new LeaguePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_league, container, false);

        initRecyclerView(view);

        mFloatingActionMenu = view.findViewById(R.id.fab_leagues_menu);
        mFABCreateLeague = view.findViewById(R.id.fab_create_league);
        mFABJoinLeague = view.findViewById(R.id.fab_join_league);

        initButtons();

        mProgressBar = view.findViewById(R.id.pB_league_fragment);
        mFragmentContainer = view.findViewById(R.id.leagues_container);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        initSwipeToRefresh();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        mUserId = sharedPreferences.getInt(USER_ID_EXTRA, 0);
        mTotalPoints = sharedPreferences.getInt(TOTAL_POINTS_EXTRA, 0);

        mLeaguePresenter.getUserLeagues(mUserId, mTotalPoints);

        return view;
    }

    @Override
    public void onDialogPositiveClick(JoinLeagueDialog dialog) {
        mLeaguePresenter.joinLeague(dialog.getCode(), mUserId);
    }

    @Override
    public void onDialogPositiveClick(CreateLeagueDialog dialog) {
        mLeaguePresenter.createLeague(dialog.getName(), mUserId);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            mProgressBar.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void presentLeagues(List<League> leagues) {
        if (rvAdapter == null) {
            rvAdapter = new RVLeagueAdapter(leagues, getContext());
            mRecyclerView.setAdapter(rvAdapter);
        } else {
            rvAdapter.refreshData(leagues);
        }
    }

    @Override
    public void reloadLeagues() {
        mLeaguePresenter.getUserLeagues(mUserId, mTotalPoints);
    }

    @Override
    public void onJoinLeagueFailure() {
        Snackbar.make(mFragmentContainer, getContext().getString(R.string.join_league_error), BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void onCreateLeagueFailure() {
        Snackbar.make(mFragmentContainer, getContext().getString(R.string.create_league_error), BaseTransientBottomBar.LENGTH_LONG).show();
    }


    private void initButtons() {
        mFABCreateLeague.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFloatingActionMenu.close(true);
                final CreateLeagueDialog createLeagueDialog = new CreateLeagueDialog();
                createLeagueDialog.setTargetFragment(LeagueFragment.this, 0);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        createLeagueDialog.show(getFragmentManager(), "league_join_dialog");
                    }
                }, 250);
            }
        });

        mFABJoinLeague.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFloatingActionMenu.close(true);
                final JoinLeagueDialog leagueDialog = new JoinLeagueDialog();
                leagueDialog.setTargetFragment(LeagueFragment.this, 0);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        leagueDialog.show(getFragmentManager(), "league_join_dialog");
                    }
                }, 250);
            }
        });
    }

    /**
     * Called when a fragment will be displayed
     */
    @Override
    public void willBeDisplayed() {
        if (mFragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
            mFragmentContainer.startAnimation(fadeIn);
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    /**
     * Called when a fragment will be hidden
     */
    @Override
    public void willBeHidden() {
        if (mFragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            mFragmentContainer.startAnimation(fadeOut);
        }
    }

    @Override
    public void setRefreshing(boolean set) {
        mSwipeRefreshLayout.setRefreshing(set);
    }

    /**
     * Prepare RecyclerView for action
     *
     * @param view parent view
     */
    private void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.rv_leagues);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        mFloatingActionMenu.showMenu(true);
                        break;
                    default:
                        mFloatingActionMenu.hideMenu(true);
                        break;
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void initSwipeToRefresh(){
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadLeagues();
            }
        });
    }

}
