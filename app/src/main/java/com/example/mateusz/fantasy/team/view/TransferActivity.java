package com.example.mateusz.fantasy.team.view;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

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

import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.TEAM_ID_EXTRA;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.USER_ID_EXTRA;

public class TransferActivity extends AppCompatActivity implements RVAllPlayerAdapter.CallbackInterface, RVSelectedPlayerAdapter.SelectedPlayersCallback, ITransferView {
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

        initViews();
        transferPresenter = new TransferPresenter(this, this);
        getPlayersToTransferFromIntent();
        transferPresenter.assignPlayersToTransfer(gkLeftTV, defLeftTV, midLeftTV, atkLeftTV, budgetTV);
        initSelectedPlayersRV();
        initAllPlayersRV();
        transferPresenter.getAllPlayers();
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
        allPlayerAdapter = new RVAllPlayerAdapter(allPlayers, this, this);
        allPlayersRV.setAdapter(allPlayerAdapter);
    }

    @Override
    public void addToSelectedPlayers(Player player) {
        transferPresenter.addToSelectedPlayers(player, selectedPlayerAdapter, gkLeftTV, defLeftTV, midLeftTV, atkLeftTV, budgetTV, makeTransfersButton);
    }

    @Override
    public void removeFromSelectedPlayers(Player player) {
        transferPresenter.removeFromSelectedPlayers(player, selectedPlayerAdapter, gkLeftTV, defLeftTV, midLeftTV, atkLeftTV, budgetTV, makeTransfersButton);
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

    private void getPlayersToTransferFromIntent() {
        Intent intent = getIntent();
        transferPresenter.getPlayersToTransferFromIntent(intent);
    }

    private void setListenerSortB() {
        sortB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) transferPresenter.sortByValue();
                else transferPresenter.sortByTotalPoints();
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
                if (position == 0) transferPresenter.sortByName();
                else if (position == 1) transferPresenter.sortByTeam();
                else transferPresenter.sortByPosition();
                if (allPlayerAdapter != null) allPlayerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setOnClickListenerToButton() {
        makeTransfersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (transferPresenter.usersTeam == null)
                    transferPresenter.usersTeam = transferPresenter.selectedPlayers;
                else {
                    transferPresenter.usersTeam.removeAll(transferPresenter.playersToTransfer);
                    transferPresenter.usersTeam.addAll(transferPresenter.selectedPlayers);
                }
                if (checkMaxNumberOfPlayers()){
                    transferPresenter.usersTeam.removeAll(transferPresenter.selectedPlayers);
                    return;
                }

                Collections.sort(transferPresenter.usersTeam);
                int teamId = getTeamIdFromSharedPreferences();
                int userId = getUserIdFromSharedPreferences();
                makeTransfersButton.setEnabled(false);
                transferPresenter.updateTeam(teamId, userId);
            }
        });
    }

    private boolean checkMaxNumberOfPlayers() {
        String check = transferPresenter.checkMaxPlayersFromEachTeam();
        if (!check.equals("OK")){
            Toast.makeText(TransferActivity.this,getString(R.string.more_than_3_error)+check,Toast.LENGTH_LONG).show();
            makeTransfersButton.setEnabled(true);
            return true;
        }
        return false;
    }

    private int getTeamIdFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        return sharedPreferences.getInt(TEAM_ID_EXTRA, 0);
    }

    private int getUserIdFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        return sharedPreferences.getInt(USER_ID_EXTRA, 0);
    }

    private void setupSort(Spinner spinner, int array) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, array, R.layout.spinner_xml);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void initSelectedPlayersRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        selectedPlayersRV.setHasFixedSize(true);
        selectedPlayersRV.setLayoutManager(linearLayoutManager);
        selectedPlayerAdapter = new RVSelectedPlayerAdapter(transferPresenter.selectedPlayers, this, this);
        selectedPlayersRV.setAdapter(selectedPlayerAdapter);
    }

    private void initAllPlayersRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allPlayersRV.setHasFixedSize(true);
        allPlayersRV.setLayoutManager(linearLayoutManager);
    }
}
