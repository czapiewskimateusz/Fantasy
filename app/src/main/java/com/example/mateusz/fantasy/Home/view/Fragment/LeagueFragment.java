package com.example.mateusz.fantasy.Home.view.Fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.mateusz.fantasy.Home.model.League;
import com.example.mateusz.fantasy.Home.presenter.RVLeagueAdapter;
import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.Utils.Dialog;



public class LeagueFragment extends Fragment{

    private Button mBtnCreateLeague;
    private Button mBtnJoinLeague;

    /**
     * Constructor
     */
    public LeagueFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_league, container, false);

        initButtons(view);
        initRecyclerView(view);

        return view;
    }

    private void initButtons(View view){

        mBtnCreateLeague = view.findViewById(R.id.btn_create_league);

        mBtnJoinLeague = view.findViewById(R.id.btn_join_league);
        mBtnJoinLeague.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog.getAlertDialog(getActivity()).show();
            }
        });
        ;

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
