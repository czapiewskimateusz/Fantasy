package com.example.mateusz.fantasy.team.view;


import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.team.model.Player;
import com.example.mateusz.fantasy.team.presenter.adapters.RVAllPlayerAdapter;
import com.example.mateusz.fantasy.team.presenter.adapters.RVSelectedPlayerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class TransferFragment extends Fragment {
    private ArrayList<Player> playersToTransfer;
    private ArrayList<Player> usersTeam;
    private ArrayList<Player> selectedPlayers;
    private ArrayList<Player> allPlayers;
    private int goalkeepers=0;
    private int midfielders=0;
    private int defenders=0;
    private int attackers=0;
    private double budget=0.0;

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

    public TransferFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transfer, container, false);

        playersToTransfer = Player.getMockToTransferData();
        selectedPlayers = Player.getMockToTransferData();
        allPlayers = Player.getMockPlayerData();

        initViews(view);
        setupSort(sortA,R.array.sortA_types);
        setupSort(sortB,R.array.sortB_types);
        setOnClickListenerToButton();
        assignPlayersToTransfer();
        initSelectedPlayersRV();
        initAllPlayersRV();
        setListenerSortA();
        setListenerSortB();
        return view;
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
                } else if (position == 1){
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
                return t1.getTotalPoints()-player.getTotalPoints();
            }
        });
    }

    private void sortByValue() {
        Collections.sort(allPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                Double d = t1.getValue()-player.getValue();
                return d.intValue();
            }
        });
    }

    private void sortByPosition() {
        Collections.sort(allPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                return player.getPosition()-t1.getPosition();
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
                return  player.getName().compareTo(t1.getName());
            }
        });
    }

    private void setOnClickListenerToButton() {
        makeTransfersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeamFragment newFragment = new TeamFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.transfer_fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void initViews(View view) {
        sortA = view.findViewById(R.id.sp_sortA);
        sortB = view.findViewById(R.id.sp_sortB);
        makeTransfersButton = view.findViewById(R.id.btn_make_transfers);
        makeTransfersButton.setEnabled(false);
        selectedPlayersRV = view.findViewById(R.id.rv_selected_players);
        allPlayersRV = view.findViewById(R.id.rv_all_players);
        gkLeftTV = view.findViewById(R.id.tv_gk_left);
        defLeftTV = view.findViewById(R.id.tv_def_left);
        midLeftTV = view.findViewById(R.id.tv_mid_left);
        atkLeftTV = view.findViewById(R.id.tv_atk_left);
        budgetTV = view.findViewById(R.id.tv_budget);
    }

    private void setupSort(Spinner spinner, int array) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),array,R.layout.spinner_xml);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void assignPlayersToTransfer() {
        for (Player p:playersToTransfer){
            if (p.getPosition()==1) goalkeepers++;
            if (p.getPosition()==2) defenders++;
            if (p.getPosition()==3) midfielders++;
            if (p.getPosition()==4) attackers++;
        }
        setColors();
        setTexts();
    }

    private void setTexts() {
        gkLeftTV.setText(String.valueOf(goalkeepers));
        defLeftTV.setText(String.valueOf(defenders));
        midLeftTV.setText(String.valueOf(midfielders));
        atkLeftTV.setText(String.valueOf(attackers));
        budgetTV.setText(String.valueOf(18.3));
    }

    private void setColors() {
        if (goalkeepers>0) gkLeftTV.setTextColor(fetchColor(android.R.color.holo_red_dark));
        else gkLeftTV.setTextColor(fetchColor(R.color.accent));
        if (defenders>0) defLeftTV.setTextColor(fetchColor(android.R.color.holo_red_dark));
        else defLeftTV.setTextColor(fetchColor(R.color.accent));
        if (midfielders>0) midLeftTV.setTextColor(fetchColor(android.R.color.holo_red_dark));
        else midLeftTV.setTextColor(fetchColor(R.color.accent));
        if (attackers>0) atkLeftTV.setTextColor(fetchColor(android.R.color.holo_red_dark));
        else atkLeftTV.setTextColor(fetchColor(R.color.accent));
        if (budget<0) budgetTV.setTextColor(fetchColor(android.R.color.holo_red_dark));
        else budgetTV.setTextColor(fetchColor(R.color.accent));
    }

    void initSelectedPlayersRV(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        selectedPlayersRV.setHasFixedSize(true);
        selectedPlayersRV.setLayoutManager(linearLayoutManager);
        selectedPlayerAdapter = new RVSelectedPlayerAdapter(selectedPlayers);
        selectedPlayersRV.setAdapter(selectedPlayerAdapter);
    }

    private void initAllPlayersRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allPlayersRV.setHasFixedSize(true);
        allPlayersRV.setLayoutManager(linearLayoutManager);
        allPlayerAdapter = new RVAllPlayerAdapter(allPlayers,getContext());
        allPlayersRV.setAdapter(allPlayerAdapter);
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(getContext(), color);
    }
}
