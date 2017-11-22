package com.example.mateusz.fantasy.home.presenter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.home.model.repo.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.mateusz.fantasy.home.model.repo.Teams.ARSENAL;
import static com.example.mateusz.fantasy.home.model.repo.Teams.BOURNEMOUTH;
import static com.example.mateusz.fantasy.home.model.repo.Teams.BRIGHTON;
import static com.example.mateusz.fantasy.home.model.repo.Teams.BURNLEY;
import static com.example.mateusz.fantasy.home.model.repo.Teams.CHELSEA;
import static com.example.mateusz.fantasy.home.model.repo.Teams.CRYSTAL_PALACE;
import static com.example.mateusz.fantasy.home.model.repo.Teams.EVERTON;
import static com.example.mateusz.fantasy.home.model.repo.Teams.HUDDERSFIELD;
import static com.example.mateusz.fantasy.home.model.repo.Teams.LEICESTER;
import static com.example.mateusz.fantasy.home.model.repo.Teams.LIVERPOOL_FC;
import static com.example.mateusz.fantasy.home.model.repo.Teams.MAN_CITY;
import static com.example.mateusz.fantasy.home.model.repo.Teams.MAN_UTD;
import static com.example.mateusz.fantasy.home.model.repo.Teams.NEWCASTLE;
import static com.example.mateusz.fantasy.home.model.repo.Teams.SOUTHAMPTON;
import static com.example.mateusz.fantasy.home.model.repo.Teams.SPURS;
import static com.example.mateusz.fantasy.home.model.repo.Teams.STOKE;
import static com.example.mateusz.fantasy.home.model.repo.Teams.SWANSEA;
import static com.example.mateusz.fantasy.home.model.repo.Teams.WATFORD;
import static com.example.mateusz.fantasy.home.model.repo.Teams.WEST_BROM;

/**
 * Created by Mateusz on 22.11.2017.
 */

public class RVFormationAdapter extends RecyclerView.Adapter<RVFormationAdapter.FormationViewHolder> {

    ArrayList<Player> formation;
    Context context;

    public RVFormationAdapter(ArrayList<Player> formation, Context context) {
        this.formation = formation;
        this.context = context;
    }

    @Override
    public FormationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_player,parent,false);
        return new FormationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FormationViewHolder holder, int position) {
        holder.name.setText(formation.get(position).getName());
        holder.score.setText(Integer.toString(formation.get(position).getPoints()));

        setPlayersKit(holder, position);
    }

    private void setPlayersKit(FormationViewHolder holder, int position) {
        if (formation.get(position).getTeam().equals(ARSENAL)) holder.kit.setImageResource(R.drawable.arsenal_1);
        if (formation.get(position).getTeam().equals(BOURNEMOUTH)) holder.kit.setImageResource(R.drawable.bournemouth_1);
        if (formation.get(position).getTeam().equals(BRIGHTON)) holder.kit.setImageResource(R.drawable.brighton_1);
        if (formation.get(position).getTeam().equals(BURNLEY)) holder.kit.setImageResource(R.drawable.burnley_1);
        if (formation.get(position).getTeam().equals(CHELSEA)) holder.kit.setImageResource(R.drawable.chelsea_1);
        if (formation.get(position).getTeam().equals(CRYSTAL_PALACE)) holder.kit.setImageResource(R.drawable.crystalpalace_1);
        if (formation.get(position).getTeam().equals(EVERTON)) holder.kit.setImageResource(R.drawable.everton_1);
        if (formation.get(position).getTeam().equals(HUDDERSFIELD)) holder.kit.setImageResource(R.drawable.huddersfield_1);
        if (formation.get(position).getTeam().equals(LEICESTER)) holder.kit.setImageResource(R.drawable.leicester_1);
        if (formation.get(position).getTeam().equals(LIVERPOOL_FC)) holder.kit.setImageResource(R.drawable.liverpool_1);
        if (formation.get(position).getTeam().equals(MAN_CITY)) holder.kit.setImageResource(R.drawable.mancity_1);
        if (formation.get(position).getTeam().equals(MAN_UTD)) holder.kit.setImageResource(R.drawable.manutd_1);
        if (formation.get(position).getTeam().equals(NEWCASTLE)) holder.kit.setImageResource(R.drawable.newcastle_1);
        if (formation.get(position).getTeam().equals(SOUTHAMPTON)) holder.kit.setImageResource(R.drawable.southampton_1);
        if (formation.get(position).getTeam().equals(SPURS)) holder.kit.setImageResource(R.drawable.spurs_1);
        if (formation.get(position).getTeam().equals(STOKE)) holder.kit.setImageResource(R.drawable.stoke_1);
        if (formation.get(position).getTeam().equals(SWANSEA)) holder.kit.setImageResource(R.drawable.swansea_1);
        if (formation.get(position).getTeam().equals(WATFORD)) holder.kit.setImageResource(R.drawable.watford_1);
        if (formation.get(position).getTeam().equals(WEST_BROM)) holder.kit.setImageResource(R.drawable.westbrom_2);
    }

    @Override
    public int getItemCount() {
        return formation.size();
    }

    /**
     * View Holder class
     */
    class FormationViewHolder extends RecyclerView.ViewHolder {
        final ImageView kit;
        final TextView name;
        final TextView score;

        public FormationViewHolder(View itemView) {
            super(itemView);

            kit = itemView.findViewById(R.id.iv_kit);
            name = itemView.findViewById(R.id.tv_player_name);
            score = itemView.findViewById(R.id.tv_player_score);
        }
    }
}
