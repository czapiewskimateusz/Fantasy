package com.example.mateusz.fantasy.team.presenter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.TextView;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.team.model.API.GetAllPlayersAPI;
import com.example.mateusz.fantasy.team.model.API.UpdateTeamAPI;
import com.example.mateusz.fantasy.team.model.repo.Player;
import com.example.mateusz.fantasy.team.model.repo.Teams;
import com.example.mateusz.fantasy.team.presenter.adapters.RVSelectedPlayerAdapter;
import com.example.mateusz.fantasy.team.view.ITransferView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.BUDGET_EXTRA;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.PREFS_NAME;
import static com.example.mateusz.fantasy.team.view.TeamFragment.PLAYERS_TO_TRANSFER_EXTRA;
import static com.example.mateusz.fantasy.team.view.TeamFragment.USERS_TEAM_EXTRA;

public class TransferPresenter {
    private ITransferView view;
    private GetAllPlayersAPI getAllPlayersAPI;
    private UpdateTeamAPI updateTeamAPI;
    private Context context;

    public ArrayList<Player> playersToTransfer;
    public ArrayList<Player> usersTeam;
    public ArrayList<Player> selectedPlayers;
    private ArrayList<Player> allPlayers;
    private int goalkeepers = 0;
    private int midfielders = 0;
    private int defenders = 0;
    private int attackers = 0;
    public float budget = 0;

    public TransferPresenter(ITransferView view, Context context) {
        this.view = view;
        this.context = context;
        getAllPlayersAPI = new GetAllPlayersAPI(this);
        updateTeamAPI = new UpdateTeamAPI(this);
        selectedPlayers = new ArrayList<>();
        budget = getBudgetFromSharedPreferences();
    }

    public void getAllPlayers() {
        view.showProgress(true);
        getAllPlayersAPI.getAllPlayers();
    }

    public void updateTeam(int teamId, int userId) {
        updateTeamAPI.updateTeam(teamId, userId, budget, usersTeam);
    }

    public void onGetAllPlayersFailure() {
        view.onGetAllPlayersFailure();
    }

    public void onGetAllPlayersSuccess(ArrayList<Player> allPlayers) {
        view.showProgress(false);
        this.allPlayers = allPlayers;
        if (playersToTransfer != null) allPlayers.removeAll(playersToTransfer);
        if (usersTeam != null) allPlayers.removeAll(usersTeam);
        view.presentAllPlayers(allPlayers);
    }

    public void onUpdateSuccess() {
        saveBudgetToSharePreferences();
        view.onUpdateSuccess();
    }

    public void onUpdateFailure() {
        view.onUpdateFailure();
    }

    public void sortByTotalPoints() {
        if (allPlayers != null) {
            Collections.sort(allPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player player, Player t1) {
                    return t1.getTotalPoints() - player.getTotalPoints();
                }
            });
        }
    }

    public void sortByValue() {
        if (allPlayers != null) {
            Collections.sort(allPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player player, Player t1) {
                    return Double.compare(t1.getValue(), player.getValue());
                }
            });
        }
    }

    public void sortByPosition() {
        if (allPlayers != null) {
            Collections.sort(allPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player player, Player t1) {
                    return player.getPosition() - t1.getPosition();
                }
            });
        }
    }

    public void sortByTeam() {
        if (allPlayers != null) {
            Collections.sort(allPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player player, Player t1) {
                    return player.getTeam().compareTo(t1.getTeam());
                }
            });
        }
    }

    public void sortByName() {
        if (allPlayers != null) {
            Collections.sort(allPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player player, Player t1) {
                    return player.getName().compareTo(t1.getName());
                }
            });
        }
    }

    private void setColors(TextView gkLeftTV, TextView defLeftTV, TextView midLeftTV, TextView atkLeftTV, TextView budgetTV) {
        if (goalkeepers > 0 || goalkeepers < 0)
            gkLeftTV.setTextColor(fetchColor(android.R.color.holo_red_dark));
        else gkLeftTV.setTextColor(fetchColor(R.color.accent));
        if (defenders > 0 || defenders < 0)
            defLeftTV.setTextColor(fetchColor(android.R.color.holo_red_dark));
        else defLeftTV.setTextColor(fetchColor(R.color.accent));
        if (midfielders > 0 || midfielders < 0)
            midLeftTV.setTextColor(fetchColor(android.R.color.holo_red_dark));
        else midLeftTV.setTextColor(fetchColor(R.color.accent));
        if (attackers > 0 || attackers < 0)
            atkLeftTV.setTextColor(fetchColor(android.R.color.holo_red_dark));
        else atkLeftTV.setTextColor(fetchColor(R.color.accent));
        if (budget < 0) budgetTV.setTextColor(fetchColor(android.R.color.holo_red_dark));
        else budgetTV.setTextColor(fetchColor(R.color.accent));
    }

    private void setTexts(TextView gkLeftTV, TextView defLeftTV, TextView midLeftTV, TextView atkLeftTV, TextView budgetTV) {
        gkLeftTV.setText(String.valueOf(goalkeepers));
        defLeftTV.setText(String.valueOf(defenders));
        midLeftTV.setText(String.valueOf(midfielders));
        atkLeftTV.setText(String.valueOf(attackers));
        budgetTV.setText(String.format(Locale.ENGLISH, "%3.1f", budget));
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }

    public void addToSelectedPlayers(Player player, RVSelectedPlayerAdapter selectedPlayerAdapter, TextView gkLeftTV, TextView defLeftTV, TextView midLeftTV, TextView atkLeftTV, TextView budgetTV, Button makeTransfersButton) {
        if (!selectedPlayers.contains(player)) {
            selectedPlayers.add(player);
            selectedPlayerAdapter.notifyDataSetChanged();
            addToTransfer(player, gkLeftTV, defLeftTV, midLeftTV, atkLeftTV, budgetTV, makeTransfersButton);
        }
    }

    public void removeFromSelectedPlayers(Player player, RVSelectedPlayerAdapter selectedPlayerAdapter, TextView gkLeftTV, TextView defLeftTV, TextView midLeftTV, TextView atkLeftTV, TextView budgetTV, Button makeTransfersButton) {
        selectedPlayers.remove(player);
        selectedPlayerAdapter.notifyDataSetChanged();
        removeFromTransfer(player, gkLeftTV, defLeftTV, midLeftTV, atkLeftTV, budgetTV, makeTransfersButton);
    }

    private void addToTransfer(Player p, TextView gkLeftTV, TextView defLeftTV, TextView midLeftTV, TextView atkLeftTV, TextView budgetTV, Button makeTransfersButton) {
        if (p.getPosition() == 1) goalkeepers--;
        if (p.getPosition() == 2) defenders--;
        if (p.getPosition() == 3) midfielders--;
        if (p.getPosition() == 4) attackers--;
        budget -= p.getValue();
        setColors(gkLeftTV, defLeftTV, midLeftTV, atkLeftTV, budgetTV);
        setTexts(gkLeftTV, defLeftTV, midLeftTV, atkLeftTV, budgetTV);
        if (checkTransferConditions()) makeTransfersButton.setEnabled(true);
        else makeTransfersButton.setEnabled(false);
    }

    private void removeFromTransfer(Player p, TextView gkLeftTV, TextView defLeftTV, TextView midLeftTV, TextView atkLeftTV, TextView budgetTV, Button makeTransfersButton) {
        if (p.getPosition() == 1) goalkeepers++;
        if (p.getPosition() == 2) defenders++;
        if (p.getPosition() == 3) midfielders++;
        if (p.getPosition() == 4) attackers++;
        budget += p.getValue();
        setColors(gkLeftTV, defLeftTV, midLeftTV, atkLeftTV, budgetTV);
        setTexts(gkLeftTV, defLeftTV, midLeftTV, atkLeftTV, budgetTV);
        if (checkTransferConditions()) makeTransfersButton.setEnabled(true);
        else makeTransfersButton.setEnabled(false);
    }

    private boolean checkTransferConditions() {
        return (goalkeepers == 0 && defenders == 0 && midfielders == 0 && attackers == 0 && budget >= 0.0);
    }

    public void assignPlayersToTransfer(TextView gkLeftTV, TextView defLeftTV, TextView midLeftTV, TextView atkLeftTV, TextView budgetTV) {
        if (playersToTransfer == null) {
            goalkeepers = 1;
            defenders = 4;
            midfielders = 4;
            attackers = 2;
            budget = 75;
        } else {
            for (Player p : playersToTransfer) {
                budget += p.getValue();
                if (p.getPosition() == 1) goalkeepers++;
                if (p.getPosition() == 2) defenders++;
                if (p.getPosition() == 3) midfielders++;
                if (p.getPosition() == 4) attackers++;
            }
        }
        setColors(gkLeftTV, defLeftTV, midLeftTV, atkLeftTV, budgetTV);
        setTexts(gkLeftTV, defLeftTV, midLeftTV, atkLeftTV, budgetTV);
    }

    @SuppressWarnings("unchecked")
    public void getPlayersToTransferFromIntent(Intent intent) {
        playersToTransfer = (ArrayList<Player>) intent.getSerializableExtra(PLAYERS_TO_TRANSFER_EXTRA);
        usersTeam = (ArrayList<Player>) intent.getSerializableExtra(USERS_TEAM_EXTRA);
    }

    private float getBudgetFromSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getFloat(BUDGET_EXTRA,0);
    }

    private void saveBudgetToSharePreferences() {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putFloat(BUDGET_EXTRA,budget);
        editor.apply();
    }

    public String checkMaxPlayersFromEachTeam() {
        int[] numberOfPlayers = new int[20];
        ArrayList<String> teams = Teams.getListOfAllTeams();

        for (Player p: usersTeam){
            for (int i =0; i<teams.size();i++)
                if (p.getTeam().equals(teams.get(i))) numberOfPlayers[i]++;
        }
        for (int i=0;i<20;i++) if (numberOfPlayers[i]>3) return teams.get(i);
        return "OK";
    }
}
