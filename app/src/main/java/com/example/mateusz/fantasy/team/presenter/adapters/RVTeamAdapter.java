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
import com.example.mateusz.fantasy.team.model.Player;

import java.util.ArrayList;

import static com.example.mateusz.fantasy.team.model.Teams.ARSENAL;
import static com.example.mateusz.fantasy.team.model.Teams.BOURNEMOUTH;
import static com.example.mateusz.fantasy.team.model.Teams.BRIGHTON;
import static com.example.mateusz.fantasy.team.model.Teams.BURNLEY;
import static com.example.mateusz.fantasy.team.model.Teams.CHELSEA;
import static com.example.mateusz.fantasy.team.model.Teams.CRYSTAL_PALACE;
import static com.example.mateusz.fantasy.team.model.Teams.EVERTON;
import static com.example.mateusz.fantasy.team.model.Teams.HUDDERSFIELD;
import static com.example.mateusz.fantasy.team.model.Teams.LEICESTER;
import static com.example.mateusz.fantasy.team.model.Teams.LIVERPOOL_FC;
import static com.example.mateusz.fantasy.team.model.Teams.MAN_CITY;
import static com.example.mateusz.fantasy.team.model.Teams.MAN_UTD;
import static com.example.mateusz.fantasy.team.model.Teams.NEWCASTLE;
import static com.example.mateusz.fantasy.team.model.Teams.SOUTHAMPTON;
import static com.example.mateusz.fantasy.team.model.Teams.SPURS;
import static com.example.mateusz.fantasy.team.model.Teams.STOKE;
import static com.example.mateusz.fantasy.team.model.Teams.SWANSEA;
import static com.example.mateusz.fantasy.team.model.Teams.WATFORD;
import static com.example.mateusz.fantasy.team.model.Teams.WEST_BROM;


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
        selectedPlayers = new ArrayList<Player>();
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
       holder.name.setText(players.get(position).getName());
       holder.score.setText(Integer.toString(players.get(position).getPoints()));
       holder.player = players.get(position);
       setPlayersKit(holder,position);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    private void setPlayersKit(TeamViewHolder holder, int position) {
        if (players.get(position).getTeam().equals(ARSENAL)) holder.kit.setImageResource(R.drawable.arsenal_1);
        if (players.get(position).getTeam().equals(BOURNEMOUTH)) holder.kit.setImageResource(R.drawable.bournemouth_1);
        if (players.get(position).getTeam().equals(BRIGHTON)) holder.kit.setImageResource(R.drawable.brighton_1);
        if (players.get(position).getTeam().equals(BURNLEY)) holder.kit.setImageResource(R.drawable.burnley_1);
        if (players.get(position).getTeam().equals(CHELSEA)) holder.kit.setImageResource(R.drawable.chelsea_1);
        if (players.get(position).getTeam().equals(CRYSTAL_PALACE)) holder.kit.setImageResource(R.drawable.crystalpalace_1);
        if (players.get(position).getTeam().equals(EVERTON)) holder.kit.setImageResource(R.drawable.everton_1);
        if (players.get(position).getTeam().equals(HUDDERSFIELD)) holder.kit.setImageResource(R.drawable.huddersfield_1);
        if (players.get(position).getTeam().equals(LEICESTER)) holder.kit.setImageResource(R.drawable.leicester_1);
        if (players.get(position).getTeam().equals(LIVERPOOL_FC)) holder.kit.setImageResource(R.drawable.liverpool_1);
        if (players.get(position).getTeam().equals(MAN_CITY)) holder.kit.setImageResource(R.drawable.mancity_1);
        if (players.get(position).getTeam().equals(MAN_UTD)) holder.kit.setImageResource(R.drawable.manutd_1);
        if (players.get(position).getTeam().equals(NEWCASTLE)) holder.kit.setImageResource(R.drawable.newcastle_1);
        if (players.get(position).getTeam().equals(SOUTHAMPTON)) holder.kit.setImageResource(R.drawable.southampton_1);
        if (players.get(position).getTeam().equals(SPURS)) holder.kit.setImageResource(R.drawable.spurs_1);
        if (players.get(position).getTeam().equals(STOKE)) holder.kit.setImageResource(R.drawable.stoke_1);
        if (players.get(position).getTeam().equals(SWANSEA)) holder.kit.setImageResource(R.drawable.swansea_1);
        if (players.get(position).getTeam().equals(WATFORD)) holder.kit.setImageResource(R.drawable.watford_1);
        if (players.get(position).getTeam().equals(WEST_BROM)) holder.kit.setImageResource(R.drawable.westbrom_2);
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
            name = itemView.findViewById(R.id.tv_player_name);
            score = itemView.findViewById(R.id.tv_player_score);
        }

        @Override
        public void onClick(View view) {
            if (alreadyClicked){
                selectedPlayers.remove(player);
                teamFragmentCallback.updatePlayersToTransfer(selectedPlayers);
                if (selectedPlayers.isEmpty()) teamFragmentCallback.setButtonEnable(false);
                name.setBackgroundColor(fetchColor(R.color.player_name_color));
                score.setBackgroundColor(fetchColor(R.color.player_score_color));
                alreadyClicked =false;
            } else {
                teamFragmentCallback.setButtonEnable(true);
                selectedPlayers.add(player);
                teamFragmentCallback.updatePlayersToTransfer(selectedPlayers);
                name.setBackgroundColor(fetchColor(R.color.player_selected));
                score.setBackgroundColor(fetchColor(R.color.player_selected));
                alreadyClicked =true;
            }
        }
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }
}
