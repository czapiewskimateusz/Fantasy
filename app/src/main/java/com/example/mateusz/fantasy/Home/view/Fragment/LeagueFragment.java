package com.example.mateusz.fantasy.Home.view.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mateusz.fantasy.Home.model.League;
import com.example.mateusz.fantasy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeagueFragment extends Fragment {

    private RecyclerView mRecyclerView;


    public LeagueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_league, container, false);

        mRecyclerView = view.findViewById(R.id.rv_leagues);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        RVAdapter rvAdapter = new RVAdapter(League.initializeData());
        mRecyclerView.setAdapter(rvAdapter);

        return view;
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.LeagueViewHolder>{

        List<League> leagues;

        RVAdapter(List<League> persons){
            this.leagues = persons;
        }

        @Override
        public LeagueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_league, parent, false);
            LeagueViewHolder pvh = new LeagueViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(LeagueViewHolder holder, int position) {
            holder.leagueName.setText(leagues.get(position).getName());
            holder.userPosition.setText(Integer.toString(leagues.get(position).getUserPosition()));
            holder.leagueCode.setText(leagues.get(position).getCode());
        }

        @Override
        public int getItemCount() {
            return leagues.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }


        class LeagueViewHolder extends RecyclerView.ViewHolder {
            final CardView cv;
            final TextView leagueName;
            final TextView userPosition;
            final TextView leagueCode;

            LeagueViewHolder(View itemView) {
                super(itemView);
                cv = itemView.findViewById(R.id.cv_league);
                leagueName = itemView.findViewById(R.id.tv_league_name);
                userPosition = itemView.findViewById(R.id.tv_ranking);
                leagueCode = itemView.findViewById(R.id.tv_league_code);
            }
        }

    }

}
