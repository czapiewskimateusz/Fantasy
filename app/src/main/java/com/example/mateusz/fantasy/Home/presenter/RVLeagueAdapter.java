package com.example.mateusz.fantasy.Home.presenter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mateusz.fantasy.Home.model.League;
import com.example.mateusz.fantasy.Home.view.LeagueDetailActivity;
import com.example.mateusz.fantasy.R;

import java.util.List;
import java.util.Locale;

import static com.example.mateusz.fantasy.Home.model.League.CODE;
import static com.example.mateusz.fantasy.Home.model.League.LEAGUE_ID;
import static com.example.mateusz.fantasy.Home.model.League.NAME;
import static com.example.mateusz.fantasy.Home.model.League.NUMBER_OF_PLAYERS;
import static com.example.mateusz.fantasy.Home.model.League.USER_POSITION;
import static com.example.mateusz.fantasy.Home.presenter.LeaguePresenter.LEAGUE_BUNDLE_EXTRA;

/**
 * RecyclerView Adapter class
 */
public class RVLeagueAdapter extends RecyclerView.Adapter<RVLeagueAdapter.LeagueViewHolder> {

    private List<League> mLeagues;

    private Context context;

    /**
     * Constructor
     * @param persons list of persons
     */
    public RVLeagueAdapter(List<League> persons, Context context) {

        this.mLeagues = persons;
        this.context = context;

    }

    @Override
    public LeagueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_league, parent, false);
        return new LeagueViewHolder(v,context);

    }

    @Override
    public void onBindViewHolder(LeagueViewHolder holder, int position) {

        holder.tvLeagueName.setText(mLeagues.get(position).getName());
        holder.tvUserPosition.setText(String.format(Locale.ENGLISH,"%d",mLeagues.get(position).getRank()));
        holder.tvLeagueCode.setText(mLeagues.get(position).getCode());
        holder.leagueId = mLeagues.get(position).getLeagueId();
        holder.numberOfPlayers = mLeagues.get(position).getNumberOfPlayers();

    }

    @Override
    public int getItemCount() {
        return mLeagues.size();
    }


    /**
     * RecyclerView.ViewHolder class
     */
    class LeagueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final CardView cv;
        final TextView tvLeagueName;
        final TextView tvUserPosition;
        final TextView tvLeagueCode;
        int leagueId;
        int numberOfPlayers;
        private final Context context;

        LeagueViewHolder(View itemView, Context context) {

            super(itemView);
            itemView.setOnClickListener(this);
            cv = itemView.findViewById(R.id.cv_league);
            tvLeagueName = itemView.findViewById(R.id.tv_league_name_detail);
            tvUserPosition = itemView.findViewById(R.id.tv_ranking);
            tvLeagueCode = itemView.findViewById(R.id.tv_league_code);
            leagueId = 0;
            numberOfPlayers = 0;
            this.context = context;
        }

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(context, LeagueDetailActivity.class);

            Bundle bundle = new Bundle();
            bundle.putInt(LEAGUE_ID,leagueId);
            bundle.putString(NAME, tvLeagueName.getText().toString());
            bundle.putInt(USER_POSITION,Integer.parseInt(tvUserPosition.getText().toString()));
            bundle.putString(CODE, tvLeagueCode.getText().toString());
            bundle.putInt(NUMBER_OF_PLAYERS,numberOfPlayers);

            intent.putExtra(LEAGUE_BUNDLE_EXTRA,bundle);
            context.startActivity(intent);

        }
    }

}