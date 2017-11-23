package com.example.mateusz.fantasy.home.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.home.model.repo.Player;
import com.example.mateusz.fantasy.home.presenter.adapters.RVTeamAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment implements ParentFragment {

    public FrameLayout fragmentContainer;
    private RecyclerView mRvTeam;
    private RVTeamAdapter rvTeamAdapter;

    public TeamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);

        fragmentContainer = view.findViewById(R.id.team_fragment_container);
        initRecyclerView(view);
        presentTeams(Player.getMockPlayerData());
        return view;
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

    void presentTeams(ArrayList<Player> players){
        if (rvTeamAdapter == null){
            rvTeamAdapter = new RVTeamAdapter(players,getContext());
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvTeam.setLayoutManager(linearLayoutManager);
        mRvTeam.setNestedScrollingEnabled(false);
        mRvTeam.setHasFixedSize(true);
    }
}
