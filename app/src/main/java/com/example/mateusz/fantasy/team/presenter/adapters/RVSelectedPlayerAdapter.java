package com.example.mateusz.fantasy.team.presenter.adapters;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.team.model.Player;

import java.util.ArrayList;
import java.util.Locale;


public class RVSelectedPlayerAdapter extends RecyclerView.Adapter<RVSelectedPlayerAdapter.SelectedPlayerViewHolder> {
    private ArrayList<Player> selectedPlayers;
    private SelectedPlayersCallback selectedPlayersCallback;
    private Context context;

    public interface SelectedPlayersCallback{
        void removeFromSelectedPlayers(Player player);
    }

    public RVSelectedPlayerAdapter(ArrayList<Player> selectedPlayers, SelectedPlayersCallback selectedPlayersCallback, Context context) {
        this.selectedPlayers = selectedPlayers;
        this.selectedPlayersCallback = selectedPlayersCallback;
        this.context = context;
    }


    @Override
    public SelectedPlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_selected_players,parent,false);
        return new SelectedPlayerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SelectedPlayerViewHolder holder, int position) {
        holder.name.setText(selectedPlayers.get(position).getName());
        holder.position.setText(Player.getPosition(selectedPlayers.get(position).getPosition()));
        setPositionColor(holder, position);
        holder.value.setText(String.format(Locale.ENGLISH,"%3.2f " +"£" ,selectedPlayers.get(position).getValue()));
        holder.player=selectedPlayers.get(position);
    }

    private void setPositionColor(SelectedPlayerViewHolder holder, int position) {
        if (selectedPlayers.get(position).getPosition()==1) holder.position.setTextColor(fetchColor(R.color.color_position_gk));
        if (selectedPlayers.get(position).getPosition()==2) holder.position.setTextColor(fetchColor(R.color.color_position_def));
        if (selectedPlayers.get(position).getPosition()==3) holder.position.setTextColor(fetchColor(R.color.color_position_mid));
        if (selectedPlayers.get(position).getPosition()==4) holder.position.setTextColor(fetchColor(R.color.color_position_atk));
    }

    @Override
    public int getItemCount() {
        return selectedPlayers.size();
    }


    /**
     * View holder class for a team
     */
    class SelectedPlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView name;
        final TextView position;
        final TextView value;
        Player player;

        SelectedPlayerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.tv_name);
            position = itemView.findViewById(R.id.tv_player_position);
            value = itemView.findViewById(R.id.tv_player_value);
        }

        @Override
        public void onClick(View view) {
           selectedPlayersCallback.removeFromSelectedPlayers(player);
        }
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }
}
