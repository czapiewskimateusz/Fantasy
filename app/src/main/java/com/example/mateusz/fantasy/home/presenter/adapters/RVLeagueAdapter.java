package com.example.mateusz.fantasy.home.presenter.adapters;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mateusz.fantasy.home.model.repo.League;
import com.example.mateusz.fantasy.home.view.LeagueDetailActivity;
import com.example.mateusz.fantasy.R;

import java.util.List;
import java.util.Locale;

import static com.example.mateusz.fantasy.home.model.repo.League.CODE;
import static com.example.mateusz.fantasy.home.model.repo.League.LEAGUE_ID;
import static com.example.mateusz.fantasy.home.model.repo.League.NAME;
import static com.example.mateusz.fantasy.home.model.repo.League.NUMBER_OF_PLAYERS;
import static com.example.mateusz.fantasy.home.model.repo.League.USER_POSITION;
import static com.example.mateusz.fantasy.home.presenter.LeaguePresenter.LEAGUE_BUNDLE_EXTRA;

/**
 * RecyclerView Adapter class
 */
public class RVLeagueAdapter extends RecyclerView.Adapter<RVLeagueAdapter.LeagueViewHolder> {

    private List<League> mLeagues;
    private Context context;

    /**
     * Constructor
     * @param leagues list of persons
     */
    public RVLeagueAdapter(List<League> leagues, Context context) {
        this.mLeagues = leagues;
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
     * Reload view with new data
     * @param leagues new data
     */
    public void refreshData(List<League> leagues){
        mLeagues.clear();
        mLeagues.addAll(leagues);
        notifyDataSetChanged();
    }


    /**
     * RecyclerView.ViewHolder class
     */
    class LeagueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final CardView cv;
        final TextView tvLeagueName;
        final TextView tvUserPosition;
        final TextView tvLeagueCode;
        final ImageView ivLeagueLogo;
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
            ivLeagueLogo = itemView.findViewById(R.id.iv_league_logo_detail);
            leagueId = 0;
            numberOfPlayers = 0;
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, LeagueDetailActivity.class);
            Bundle bundle = prepareBundle();
            intent.putExtra(LEAGUE_BUNDLE_EXTRA,bundle);
            ActivityOptionsCompat activityOptions = getActivityOptionsCompat();
            context.startActivity(intent, activityOptions.toBundle());
        }

        @NonNull
        private ActivityOptionsCompat getActivityOptionsCompat() {
            View sharedViewLogo = ivLeagueLogo;
            View sharedViewName = tvLeagueName;
            Pair<View, String> p1 = Pair.create(sharedViewLogo, "tran_cup");
            Pair<View, String> p2 = Pair.create(sharedViewName, "tran_league_name");
            return ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,p1,p2);
        }

        @NonNull
        private Bundle prepareBundle() {
            Bundle bundle = new Bundle();
            bundle.putInt(LEAGUE_ID,leagueId);
            bundle.putString(NAME, tvLeagueName.getText().toString());
            bundle.putInt(USER_POSITION,Integer.parseInt(tvUserPosition.getText().toString()));
            bundle.putString(CODE, tvLeagueCode.getText().toString());
            bundle.putInt(NUMBER_OF_PLAYERS,numberOfPlayers);
            return bundle;
        }
    }

}