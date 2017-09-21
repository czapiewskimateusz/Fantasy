package com.example.mateusz.fantasy.Home.view.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mateusz.fantasy.Home.model.League;
import com.example.mateusz.fantasy.Home.presenter.RVLeagueAdapter;
import com.example.mateusz.fantasy.R;


public class LeagueFragment extends Fragment{

    /**
     * Constructor
     */
    public LeagueFragment() {

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
     * @param view parent view
     */
    private void initRecyclerView(View view){

        RecyclerView mRecyclerView;
        mRecyclerView = view.findViewById(R.id.rv_leagues);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(linearLayoutManager);

        RVLeagueAdapter rvAdapter = new RVLeagueAdapter(League.initializeData(),getContext());
        mRecyclerView.setAdapter(rvAdapter);

    }

}
