package com.example.mateusz.fantasy.team.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.home.view.fragment.ParentFragment;
import com.example.mateusz.fantasy.team.model.Player;
import com.example.mateusz.fantasy.team.presenter.adapters.RVTeamAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment implements ParentFragment,RVTeamAdapter.TeamFragmentCallback {

    public FrameLayout fragmentContainer;
    private RecyclerView mRvTeam;
    private RVTeamAdapter rvTeamAdapter;
    private Button transferButton;
    private ArrayList<Player> playersToTransfer;

    public TeamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        initViews(view);
        initRecyclerView(view);
        presentTeams(Player.getMockPlayerData());
        return view;
    }

    private void initViews(View view) {
        fragmentContainer = view.findViewById(R.id.team_fragment_container);
        transferButton = view.findViewById(R.id.btn_transfer);
        transferButton.setEnabled(false);
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick();
            }
        });
    }

    /**
     * Called when a fragment will be displayed
     */
    @Override
    public void willBeDisplayed() {
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    /**
     * Called when a fragment will be hidden
     */
    @Override
    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }
    }

    void presentTeams(ArrayList<Player> players) {
        if (rvTeamAdapter == null) {
            rvTeamAdapter = new RVTeamAdapter(players, getContext(),this);
            mRvTeam.setAdapter(rvTeamAdapter);
        }
    }

    /**
     * Prepare RecyclerView for action
     *
     * @param view parent view
     */
    private void initRecyclerView(View view) {
        mRvTeam = view.findViewById(R.id.rv_team);
        GridLayoutManager gridLayoutManager = getGridLayoutManager(view);
        mRvTeam.setLayoutManager(gridLayoutManager);
        mRvTeam.setNestedScrollingEnabled(false);
        mRvTeam.setHasFixedSize(true);
    }

    @NonNull
    private GridLayoutManager getGridLayoutManager(View view) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) return 4;
                if (position > 0 && position < 9) return 1;
                return 2;
            }
        });
        return gridLayoutManager;
    }

    public void onButtonClick(){
        Intent intent = new Intent(getContext(),TransferActivity.class);
        getContext().startActivity(intent);
    }

    @Override
    public void setButtonEnable(boolean set) {
        transferButton.setEnabled(set);
    }

    @Override
    public void updatePlayersToTransfer(ArrayList<Player> playersToTransfer) {
        this.playersToTransfer = playersToTransfer;
    }
}
