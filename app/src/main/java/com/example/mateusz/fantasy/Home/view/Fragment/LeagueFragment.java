package com.example.mateusz.fantasy.Home.view.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.mateusz.fantasy.Home.model.League;
import com.example.mateusz.fantasy.Home.presenter.LeaguePresenter;
import com.example.mateusz.fantasy.Home.presenter.RVLeagueAdapter;
import com.example.mateusz.fantasy.Home.view.HomeActivity;
import com.example.mateusz.fantasy.R;

import java.util.List;


public class LeagueFragment extends Fragment implements RVLeagueAdapter.LeagueAdapterCallback{

    private RecyclerView mRecyclerView;

    private LeagueFragmentCallback homeActivityCallback;

    public interface LeagueFragmentCallback{

        void onLeagueRecyclerViewItemClicked(League league);

    }

    /**
     * Constructor
     */
    public LeagueFragment() {

    }

    public void setHomeActivityCallback(LeagueFragmentCallback homeActivityCallback){
        this.homeActivityCallback = homeActivityCallback;
    }


    @Override
    public void onLeagueRecyclerViewItemClick(int leagueId, String leagueName, int userPosition, String leagueCode, int numberOfPlayers) {

        League league = new League(leagueName,userPosition,leagueCode,leagueId,numberOfPlayers);
        homeActivityCallback.onLeagueRecyclerViewItemClicked(league);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_league, container, false);

        initRecyclerView(view);

        return view;
    }

    /**
     * Prepare RecyclerView for action
     * @param view
     */
    private void initRecyclerView(View view){

        mRecyclerView = view.findViewById(R.id.rv_leagues);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(linearLayoutManager);

        RVLeagueAdapter rvAdapter = new RVLeagueAdapter(League.initializeData(),this);
        mRecyclerView.setAdapter(rvAdapter);


    }

}
