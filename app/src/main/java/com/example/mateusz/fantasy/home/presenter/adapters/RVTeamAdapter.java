package com.example.mateusz.fantasy.home.presenter.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.home.model.repo.Player;

import java.util.ArrayList;
import java.util.List;

import static com.example.mateusz.fantasy.home.model.repo.Player.ATTACKER;
import static com.example.mateusz.fantasy.home.model.repo.Player.DEFENDER;
import static com.example.mateusz.fantasy.home.model.repo.Player.GOALKEEPER;
import static com.example.mateusz.fantasy.home.model.repo.Player.MIDFIELDER;


public class RVTeamAdapter extends RecyclerView.Adapter<RVTeamAdapter.TeamViewHolder> {
    private ArrayList<Player> players;
    private ArrayList<Player> goalkeepers;
    private ArrayList<Player> defenders;
    private ArrayList<Player> midfielders;
    private ArrayList<Player> attackers;

    private RVFormationAdapter rvFormationAdapter;
    private Context context;

    public RVTeamAdapter(ArrayList<Player> players, Context context) {
        this.players = players;
        this.context = context;

        assignPlayerToFormation(players);
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_formation_line, parent, false);
        return new TeamViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        holder.rvFormationLine.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.rvFormationLine.setNestedScrollingEnabled(false);
        holder.rvFormationLine.setHasFixedSize(true);

        switch (position) {
            case 0:
                holder.rvFormationLine.setAdapter(new RVFormationAdapter(goalkeepers, context));
                break;
            case 1:
                holder.rvFormationLine.setAdapter(new RVFormationAdapter(defenders, context));
                break;
            case 2:
                holder.rvFormationLine.setAdapter(new RVFormationAdapter(midfielders, context));
                break;
            case 3:
                holder.rvFormationLine.setAdapter(new RVFormationAdapter(attackers, context));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    private void assignPlayerToFormation(ArrayList<Player> players) {
        initArrayLists();

        for (Player p : players) {
            if (p.getPosition().equals(GOALKEEPER)) goalkeepers.add(p);
            if (p.getPosition().equals(DEFENDER)) defenders.add(p);
            if (p.getPosition().equals(MIDFIELDER)) midfielders.add(p);
            if (p.getPosition().equals(ATTACKER)) attackers.add(p);
        }
    }

    private void initArrayLists() {
        goalkeepers = new ArrayList<Player>();
        defenders = new ArrayList<Player>();
        midfielders = new ArrayList<Player>();
        attackers = new ArrayList<Player>();
    }

    /**
     * View holder class for a team
     */
    class TeamViewHolder extends RecyclerView.ViewHolder {
        final RecyclerView rvFormationLine;

        TeamViewHolder(View itemView) {
            super(itemView);
            rvFormationLine = itemView.findViewById(R.id.rv_position_line);
        }
    }
}
