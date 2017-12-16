package com.example.mateusz.fantasy.team.presenter.adapters;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.team.model.repo.Player;

import java.util.ArrayList;
import java.util.Locale;


public class RVAllPlayerAdapter extends RecyclerView.Adapter<RVAllPlayerAdapter.AllPlayerViewHolder> {
    private ArrayList<Player> allPlayers;
    private Context context;
    private CallbackInterface callbackInterface;

    public interface CallbackInterface{
        void addToSelectedPlayers(Player player);
    }

    public RVAllPlayerAdapter(ArrayList<Player> allPlayers, Context context, CallbackInterface callbackInterface) {
        this.allPlayers = allPlayers;
        this.context = context;
        this.callbackInterface = callbackInterface;
    }


    @Override
    public AllPlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_all_players,parent,false);
        return new AllPlayerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AllPlayerViewHolder holder, int position) {
        if (position%2==0) holder.container.setBackgroundColor(fetchColor(R.color.color_trans_primary_even_darker));
        holder.name.setText(allPlayers.get(position).getName());
        holder.position.setText(Player.getPosition(allPlayers.get(position).getPosition()));
        holder.value.setText(String.format(Locale.ENGLISH,"%3.2f " + "£",allPlayers.get(position).getValue()));
        holder.totalPoints.setText(String.format(Locale.ENGLISH,"%d " + "pts",allPlayers.get(position).getTotalPoints()));
        holder.player = allPlayers.get(position);
        setPositionColor(holder, position);
    }

    private void setPositionColor(AllPlayerViewHolder holder, int position) {
        if (allPlayers.get(position).getPosition()==1) holder.position.setTextColor(fetchColor(R.color.color_position_gk));
        if (allPlayers.get(position).getPosition()==2) holder.position.setTextColor(fetchColor(R.color.color_position_def));
        if (allPlayers.get(position).getPosition()==3) holder.position.setTextColor(fetchColor(R.color.color_position_mid));
        if (allPlayers.get(position).getPosition()==4) holder.position.setTextColor(fetchColor(R.color.color_position_atk));
    }

    @Override
    public int getItemCount() {
        return allPlayers.size();
    }


    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }

    /**
     * View holder class for a team
     */
    class AllPlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final ConstraintLayout container;
        final TextView name;
        final TextView position;
        final TextView value;
        final TextView totalPoints;
        Player player;

        AllPlayerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.tv_name);
            position = itemView.findViewById(R.id.tv_position);
            value = itemView.findViewById(R.id.tv_value);
            totalPoints = itemView.findViewById(R.id.tv_total_points);
            container = itemView.findViewById(R.id.all_players_rv_container);
        }

        @Override
        public void onClick(View view) {
            callbackInterface.addToSelectedPlayers(player);
        }
    }
}
