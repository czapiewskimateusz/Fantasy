package com.example.mateusz.fantasy.Home.presenter;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mateusz.fantasy.Home.model.League;
import com.example.mateusz.fantasy.R;

import java.util.List;

/**
 * RecyclerView Adapter class
 */
public class RVLeagueAdapter extends RecyclerView.Adapter<RVLeagueAdapter.LeagueViewHolder> {

    private List<League> mLeagues;

    private LeagueAdapterCallback mLeagueAdapterCallback;

    /**
     * Interface to perform onclick
     */
    public interface LeagueAdapterCallback {
        public void onLeagueRecyclerViewItemClick(int leagueId, String leagueName, int userPosition, String leagueCode, int numberOfPlayers);
    }

    /**
     * Constructor
     * @param persons
     * @param mLeagueAdapterCallback
     */
    public RVLeagueAdapter(List<League> persons, LeagueAdapterCallback mLeagueAdapterCallback) {

        this.mLeagues = persons;
        this.mLeagueAdapterCallback = mLeagueAdapterCallback;

    }

    @Override
    public LeagueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_league, parent, false);
        LeagueViewHolder pvh = new LeagueViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(LeagueViewHolder holder, int position) {
        holder.leagueName.setText(mLeagues.get(position).getName());
        holder.userPosition.setText(Integer.toString(mLeagues.get(position).getUserPosition()));
        holder.leagueCode.setText(mLeagues.get(position).getCode());
        holder.leagueId = mLeagues.get(position).getLeague_id();
        holder.numberOfPlayers = mLeagues.get(position).getNumberOfPlayers();
    }

    @Override
    public int getItemCount() {
        return mLeagues.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * RecyclerView.ViewHolder class
     */
    class LeagueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final CardView cv;
        final TextView leagueName;
        final TextView userPosition;
        final TextView leagueCode;
        int leagueId;
        int numberOfPlayers;

        LeagueViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cv = itemView.findViewById(R.id.cv_league);
            leagueName = itemView.findViewById(R.id.tv_league_name_detail);
            userPosition = itemView.findViewById(R.id.tv_ranking);
            leagueCode = itemView.findViewById(R.id.tv_league_code);
            leagueId = 0;
            numberOfPlayers = 0;
        }

        @Override
        public void onClick(View view) {
            mLeagueAdapterCallback.onLeagueRecyclerViewItemClick(leagueId,leagueName.getText().toString(), Integer.parseInt(userPosition.getText().toString()),leagueCode.getText().toString(),numberOfPlayers);
        }
    }

}