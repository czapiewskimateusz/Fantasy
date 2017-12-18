package com.example.mateusz.fantasy.team.view;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.authentication.login.view.LoginActivity;
import com.example.mateusz.fantasy.home.view.HomeActivity;
import com.example.mateusz.fantasy.team.model.repo.Player;
import com.example.mateusz.fantasy.team.presenter.TransferPresenter;
import com.example.mateusz.fantasy.team.presenter.adapters.RVAllPlayerAdapter;
import com.example.mateusz.fantasy.team.presenter.adapters.RVSelectedPlayerAdapter;
import com.example.mateusz.fantasy.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.BUDGET_EXTRA;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.TEAM_ID_EXTRA;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.USER_ID_EXTRA;
import static com.example.mateusz.fantasy.team.view.TeamFragment.PLAYERS_TO_TRANSFER_EXTRA;
import static com.example.mateusz.fantasy.team.view.TeamFragment.USERS_TEAM_EXTRA;

public class TransferActivity extends AppCompatActivity implements RVAllPlayerAdapter.CallbackInterface, RVSelectedPlayerAdapter.SelectedPlayersCallback, ITransferView {
    private ArrayList<Player> playersToTransfer;
    private ArrayList<Player> usersTeam;
    private ArrayList<Player> selectedPlayers;
    private ArrayList<Player> allPlayers;
    private int goalkeepers = 0;
    private int midfielders = 0;
    private int defenders = 0;
    private int attackers = 0;
    private float budget = 0;

    private Button makeTransfersButton;
    private Spinner sortA;
    private Spinner sortB;
    private TextView gkLeftTV;
    private TextView defLeftTV;
    private TextView midLeftTV;
    private TextView atkLeftTV;
    private TextView budgetTV;
    private ProgressBar transferPB;

    private RecyclerView selectedPlayersRV;
    private RecyclerView allPlayersRV;
    private RVSelectedPlayerAdapter selectedPlayerAdapter;
    private RVAllPlayerAdapter allPlayerAdapter;

    private TransferPresenter transferPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        selectedPlayers = new ArrayList<>();
        initViews();
        getPlayersToTransferFromIntent();
        assignPlayersToTransfer();
        initSelectedPlayersRV();
        initAllPlayersRV();
        transferPresenter = new TransferPresenter(this, this);
        transferPresenter.getAllPlayers();
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            allPlayersRV.setVisibility(View.INVISIBLE);
            transferPB.setVisibility(View.VISIBLE);
        } else {
            allPlayersRV.setVisibility(View.VISIBLE);
            transferPB.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void presentAllPlayers(ArrayList<Player> allPlayers) {
        this.allPlayers = allPlayers;
        if (playersToTransfer != null) allPlayers.removeAll(playersToTransfer);
        if (usersTeam != null) allPlayers.removeAll(usersTeam);
        allPlayerAdapter = new RVAllPlayerAdapter(allPlayers, this, this);
        allPlayersRV.setAdapter(allPlayerAdapter);
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

    @Override
    public void onGetAllPlayersFailure() {
        NetworkUtils.showConnectionErrorToast(this);
        transferPresenter.getAllPlayers();
    }

    @Override
    public void onUpdateSuccess() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onUpdateFailure() {
        NetworkUtils.showConnectionErrorToast(this);
        makeTransfersButton.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    private void getPlayersToTransferFromIntent() {
        Intent intent = getIntent();
        playersToTransfer = (ArrayList<Player>) intent.getSerializableExtra(PLAYERS_TO_TRANSFER_EXTRA);
        usersTeam = (ArrayList<Player>) intent.getSerializableExtra(USERS_TEAM_EXTRA);
        budget = intent.getFloatExtra(BUDGET_EXTRA, 0);
    }

    private void setListenerSortB() {
        sortB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) sortByValue();
                else sortByTotalPoints();
                if (allPlayerAdapter != null) allPlayerAdapter.notifyDataSetChanged();
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
                if (position == 0) sortByName();
                else if (position == 1) sortByTeam();
                else sortByPosition();
                if (allPlayerAdapter != null) allPlayerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void sortByTotalPoints() {
        if (allPlayers != null) {
            Collections.sort(allPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player player, Player t1) {
                    return t1.getTotalPoints() - player.getTotalPoints();
                }
            });
        }
    }

    private void sortByValue() {
        if (allPlayers != null) {
            Collections.sort(allPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player player, Player t1) {
                    return Double.compare(t1.getValue(), player.getValue());
                }
            });
        }
    }

    private void sortByPosition() {
        if (allPlayers != null) {
            Collections.sort(allPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player player, Player t1) {
                    return player.getPosition() - t1.getPosition();
                }
            });
        }
    }

    private void sortByTeam() {
        if (allPlayers != null) {
            Collections.sort(allPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player player, Player t1) {
                    return player.getTeam().compareTo(t1.getTeam());
                }
            });
        }

    }

    private void sortByName() {
        if (allPlayers != null) {
            Collections.sort(allPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player player, Player t1) {
                    return player.getName().compareTo(t1.getName());
                }
            });
        }
    }

    private void setOnClickListenerToButton() {
        makeTransfersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usersTeam == null) {
                    usersTeam = selectedPlayers;
                } else {
                    usersTeam.removeAll(playersToTransfer);
                    usersTeam.addAll(selectedPlayers);
                }
                Collections.sort(usersTeam);
                int teamId = getTeamIdFromSharedPreferences();
                int userId = getUserIdFromSharedPreferences();
                makeTransfersButton.setEnabled(false);
                transferPresenter.updateTeam(teamId, userId, budget, usersTeam);
            }
        });
    }

    private int getTeamIdFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        return sharedPreferences.getInt(TEAM_ID_EXTRA, 0);
    }

    private int getUserIdFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        return sharedPreferences.getInt(USER_ID_EXTRA, 0);
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
        transferPB = findViewById(R.id.pb_transfer);

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
        if (playersToTransfer == null) {
            goalkeepers = 1;
            defenders = 4;
            midfielders = 4;
            attackers = 2;
        } else {
            for (Player p : playersToTransfer) {
                budget += p.getValue();
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
        return (goalkeepers == 0 && defenders == 0 && midfielders == 0 && attackers == 0 && budget >= 0.0);
    }

    private void setTexts() {
        gkLeftTV.setText(String.valueOf(goalkeepers));
        defLeftTV.setText(String.valueOf(defenders));
        midLeftTV.setText(String.valueOf(midfielders));
        atkLeftTV.setText(String.valueOf(attackers));
        budgetTV.setText(String.format(Locale.ENGLISH, "%3.1f", budget));
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
        selectedPlayerAdapter = new RVSelectedPlayerAdapter(selectedPlayers, this, this);
        selectedPlayersRV.setAdapter(selectedPlayerAdapter);
    }

    private void initAllPlayersRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allPlayersRV.setHasFixedSize(true);
        allPlayersRV.setLayoutManager(linearLayoutManager);
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }
}
