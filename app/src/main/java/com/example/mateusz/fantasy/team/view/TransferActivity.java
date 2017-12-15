package com.example.mateusz.fantasy.team.view;

import android.content.Intent;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.home.view.HomeActivity;
import com.example.mateusz.fantasy.team.model.Player;
import com.example.mateusz.fantasy.team.presenter.adapters.RVAllPlayerAdapter;
import com.example.mateusz.fantasy.team.presenter.adapters.RVSelectedPlayerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class TransferActivity extends AppCompatActivity implements RVAllPlayerAdapter.CallbackInterface, RVSelectedPlayerAdapter.SelectedPlayersCallback {
    private ArrayList<Player> playersToTransfer;
    private ArrayList<Player> usersTeam;
    private ArrayList<Player> selectedPlayers;
    private ArrayList<Player> allPlayers;
    private int goalkeepers = 0;
    private int midfielders = 0;
    private int defenders = 0;
    private int attackers = 0;
    private double budget = 0.0;

    private Button makeTransfersButton;
    private Spinner sortA;
    private Spinner sortB;
    private TextView gkLeftTV;
    private TextView defLeftTV;
    private TextView midLeftTV;
    private TextView atkLeftTV;
    private TextView budgetTV;

    private RecyclerView selectedPlayersRV;
    private RecyclerView allPlayersRV;
    private RVSelectedPlayerAdapter selectedPlayerAdapter;
    private RVAllPlayerAdapter allPlayerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
//        playersToTransfer = Player.getMockToTransferData();
        selectedPlayers = new ArrayList<>();
        allPlayers = Player.getMockTransferData();
        budget = 100.00;
        initViews();
        assignPlayersToTransfer();
        initSelectedPlayersRV();
        initAllPlayersRV();
    }

    @Override
    public void addToSelectedPlayers(Player player) {
        if (!selectedPlayers.contains(player)) {
            selectedPlayers.add(player);
            selectedPlayerAdapter.notifyDataSetChanged();
            addToTransfer(player);
        }
    }

    @Override
    public void removeFromSelectedPlayers(Player player) {
        selectedPlayers.remove(player);
        selectedPlayerAdapter.notifyDataSetChanged();
        removeFromTransfer(player);
    }

    private void setListenerSortB() {
        sortB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    sortByValue();
                } else {
                    sortByTotalPoints();
                }
                allPlayerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setListenerSortA() {
        sortA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    sortByName();
                } else if (position == 1) {
                    sortByTeam();
                } else {
                    sortByPosition();
                }
                allPlayerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void sortByTotalPoints() {
        Collections.sort(allPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                return t1.getTotalPoints() - player.getTotalPoints();
            }
        });
    }

    private void sortByValue() {
        Collections.sort(allPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                Double d = t1.getValue() - player.getValue();
                return d.intValue();
            }
        });
    }

    private void sortByPosition() {
        Collections.sort(allPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                return player.getPosition() - t1.getPosition();
            }
        });
    }

    private void sortByTeam() {
        Collections.sort(allPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                return player.getTeam().compareTo(t1.getTeam());
            }
        });
    }

    private void sortByName() {
        Collections.sort(allPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                return player.getName().compareTo(t1.getName());
            }
        });
    }

    private void setOnClickListenerToButton() {
        makeTransfersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransferActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        sortA = findViewById(R.id.sp_sortA);
        sortB = findViewById(R.id.sp_sortB);
        makeTransfersButton = findViewById(R.id.btn_make_transfers);
        makeTransfersButton.setEnabled(false);
        selectedPlayersRV = findViewById(R.id.rv_selected_players);
        allPlayersRV = findViewById(R.id.rv_all_players);
        gkLeftTV = findViewById(R.id.tv_gk_left);
        defLeftTV = findViewById(R.id.tv_def_left);
        midLeftTV = findViewById(R.id.tv_mid_left);
        atkLeftTV = findViewById(R.id.tv_atk_left);
        budgetTV = findViewById(R.id.tv_budget);

        setupSort(sortA, R.array.sortA_types);
        setupSort(sortB, R.array.sortB_types);
        setListenerSortA();
        setListenerSortB();
        setOnClickListenerToButton();
    }

    private void setupSort(Spinner spinner, int array) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, array, R.layout.spinner_xml);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void assignPlayersToTransfer() {
        if (playersToTransfer == null){
            goalkeepers = 1;
            defenders = 4;
            midfielders = 4;
            attackers = 2;
        } else {
            for (Player p : playersToTransfer) {
                if (p.getPosition() == 1) goalkeepers++;
                if (p.getPosition() == 2) defenders++;
                if (p.getPosition() == 3) midfielders++;
                if (p.getPosition() == 4) attackers++;
            }
        }
        setColors();
        setTexts();
    }

    private void addToTransfer(Player p) {
        if (p.getPosition() == 1) goalkeepers--;
        if (p.getPosition() == 2) defenders--;
        if (p.getPosition() == 3) midfielders--;
        if (p.getPosition() == 4) attackers--;
        budget -= p.getValue();
        setColors();
        setTexts();
        if (checkTransferConditions()) makeTransfersButton.setEnabled(true);
        else makeTransfersButton.setEnabled(false);
    }

    private void removeFromTransfer(Player p) {
        if (p.getPosition() == 1) goalkeepers++;
        if (p.getPosition() == 2) defenders++;
        if (p.getPosition() == 3) midfielders++;
        if (p.getPosition() == 4) attackers++;
        budget += p.getValue();
        setColors();
        setTexts();
        if (checkTransferConditions()) makeTransfersButton.setEnabled(true);
        else makeTransfersButton.setEnabled(false);
    }

    private boolean checkTransferConditions() {
        if (goalkeepers == 0 && (defenders == 0) && (midfielders == 0) && (attackers == 0) && (budget >= 0.0))
            return true;
        return false;
    }

    private void setTexts() {
        gkLeftTV.setText(String.valueOf(goalkeepers));
        defLeftTV.setText(String.valueOf(defenders));
        midLeftTV.setText(String.valueOf(midfielders));
        atkLeftTV.setText(String.valueOf(attackers));
        budgetTV.setText(String.format(Locale.ENGLISH,"%3.1f",budget));
    }

    private void setColors() {
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

    void initSelectedPlayersRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        selectedPlayersRV.setHasFixedSize(true);
        selectedPlayersRV.setLayoutManager(linearLayoutManager);
        selectedPlayerAdapter = new RVSelectedPlayerAdapter(selectedPlayers, this);
        selectedPlayersRV.setAdapter(selectedPlayerAdapter);
    }

    private void initAllPlayersRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allPlayersRV.setHasFixedSize(true);
        allPlayersRV.setLayoutManager(linearLayoutManager);
        allPlayerAdapter = new RVAllPlayerAdapter(allPlayers, this, this);
        allPlayersRV.setAdapter(allPlayerAdapter);
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }
}
