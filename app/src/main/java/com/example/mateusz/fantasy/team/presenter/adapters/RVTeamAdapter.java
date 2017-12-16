package com.example.mateusz.fantasy.team.presenter.adapters;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.team.model.repo.Player;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.mateusz.fantasy.team.model.repo.Teams.ARSENAL;
import static com.example.mateusz.fantasy.team.model.repo.Teams.BOURNEMOUTH;
import static com.example.mateusz.fantasy.team.model.repo.Teams.BRIGHTON;
import static com.example.mateusz.fantasy.team.model.repo.Teams.BURNLEY;
import static com.example.mateusz.fantasy.team.model.repo.Teams.CHELSEA;
import static com.example.mateusz.fantasy.team.model.repo.Teams.CRYSTAL_PALACE;
import static com.example.mateusz.fantasy.team.model.repo.Teams.EVERTON;
import static com.example.mateusz.fantasy.team.model.repo.Teams.HUDDERSFIELD;
import static com.example.mateusz.fantasy.team.model.repo.Teams.LEICESTER;
import static com.example.mateusz.fantasy.team.model.repo.Teams.LIVERPOOL_FC;
import static com.example.mateusz.fantasy.team.model.repo.Teams.MAN_CITY;
import static com.example.mateusz.fantasy.team.model.repo.Teams.MAN_UTD;
import static com.example.mateusz.fantasy.team.model.repo.Teams.NEWCASTLE;
import static com.example.mateusz.fantasy.team.model.repo.Teams.SOUTHAMPTON;
import static com.example.mateusz.fantasy.team.model.repo.Teams.SPURS;
import static com.example.mateusz.fantasy.team.model.repo.Teams.STOKE;
import static com.example.mateusz.fantasy.team.model.repo.Teams.SWANSEA;
import static com.example.mateusz.fantasy.team.model.repo.Teams.WATFORD;
import static com.example.mateusz.fantasy.team.model.repo.Teams.WEST_BROM;


public class RVTeamAdapter extends RecyclerView.Adapter<RVTeamAdapter.TeamViewHolder> {
    private ArrayList<Player> players;
    private ArrayList<Player> selectedPlayers;
    private Context context;
    private TeamFragmentCallback teamFragmentCallback;

    public interface TeamFragmentCallback{
        void setButtonEnable(boolean set);
        void updatePlayersToTransfer(ArrayList<Player> playersToTransfer);
    }

    public RVTeamAdapter(ArrayList<Player> players, Context context, TeamFragmentCallback teamFragmentCallback) {
        this.players = players;
        selectedPlayers = new ArrayList<>();
        this.context = context;
        this.teamFragmentCallback = teamFragmentCallback;
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_player,parent,false);
        return new TeamViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        players.get(position);
        holder.name.setText(players.get(position).getName());
        holder.score.setText(String.format(Locale.ENGLISH,"%d",players.get(position).getCurrentGWPoints()));
        holder.player = players.get(position);
        setPlayersKit(holder,players.get(position));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    private void setPlayersKit(TeamViewHolder holder, Player player) {
        if (player.getTeam().equals(ARSENAL)) holder.kit.setImageResource(R.drawable.arsenal_1);
        if (player.getTeam().equals(BOURNEMOUTH)) holder.kit.setImageResource(R.drawable.bournemouth_1);
        if (player.getTeam().equals(BRIGHTON)) holder.kit.setImageResource(R.drawable.brighton_1);
        if (player.getTeam().equals(BURNLEY)) holder.kit.setImageResource(R.drawable.burnley_1);
        if (player.getTeam().equals(CHELSEA)) holder.kit.setImageResource(R.drawable.chelsea_1);
        if (player.getTeam().equals(CRYSTAL_PALACE)) holder.kit.setImageResource(R.drawable.crystalpalace_1);
        if (player.getTeam().equals(EVERTON)) holder.kit.setImageResource(R.drawable.everton_1);
        if (player.getTeam().equals(HUDDERSFIELD)) holder.kit.setImageResource(R.drawable.huddersfield_1);
        if (player.getTeam().equals(LEICESTER)) holder.kit.setImageResource(R.drawable.leicester_1);
        if (player.getTeam().equals(LIVERPOOL_FC)) holder.kit.setImageResource(R.drawable.liverpool_1);
        if (player.getTeam().equals(MAN_CITY)) holder.kit.setImageResource(R.drawable.mancity_1);
        if (player.getTeam().equals(MAN_UTD)) holder.kit.setImageResource(R.drawable.manutd_1);
        if (player.getTeam().equals(NEWCASTLE)) holder.kit.setImageResource(R.drawable.newcastle_1);
        if (player.getTeam().equals(SOUTHAMPTON)) holder.kit.setImageResource(R.drawable.southampton_1);
        if (player.getTeam().equals(SPURS)) holder.kit.setImageResource(R.drawable.spurs_1);
        if (player.getTeam().equals(STOKE)) holder.kit.setImageResource(R.drawable.stoke_1);
        if (player.getTeam().equals(SWANSEA)) holder.kit.setImageResource(R.drawable.swansea_1);
        if (player.getTeam().equals(WATFORD)) holder.kit.setImageResource(R.drawable.watford_1);
        if (player.getTeam().equals(WEST_BROM)) holder.kit.setImageResource(R.drawable.westbrom_2);
    }

    /**
     * View holder class for a team
     */
    class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView kit;
        final TextView name;
        final TextView score;
        boolean alreadyClicked = false;
        Player player;

        TeamViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            kit = itemView.findViewById(R.id.iv_kit);
            name = itemView.findViewById(R.id.tv_name);
            score = itemView.findViewById(R.id.tv_player_score);
        }

        @Override
        public void onClick(View view) {
            if (alreadyClicked){
                alreadyClicked =false;
                selectedPlayers.remove(player);
                teamFragmentCallback.updatePlayersToTransfer(selectedPlayers);
                name.setBackgroundColor(fetchColor(R.color.player_name_color));
                name.setTextColor(fetchColor(R.color.colorWhite));
                score.setBackgroundColor(fetchColor(R.color.player_score_color));
                score.setTextColor(fetchColor(R.color.colorWhite));
                if (selectedPlayers.isEmpty()) teamFragmentCallback.setButtonEnable(false);
            } else {
                alreadyClicked =true;
                selectedPlayers.add(player);
                teamFragmentCallback.updatePlayersToTransfer(selectedPlayers);
                name.setBackgroundColor(fetchColor(R.color.player_selected));
                name.setTextColor(fetchColor(R.color.primary_dark));
                score.setBackgroundColor(fetchColor(R.color.player_selected));
                score.setTextColor(fetchColor(R.color.primary_dark));
                teamFragmentCallback.setButtonEnable(true);
            }
        }

    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }
}
