package com.example.mateusz.fantasy.team.view;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.authentication.login.view.LoginActivity;
import com.example.mateusz.fantasy.home.view.fragment.ParentFragment;
import com.example.mateusz.fantasy.team.model.repo.Player;
import com.example.mateusz.fantasy.team.presenter.TeamPresenter;
import com.example.mateusz.fantasy.team.presenter.adapters.RVTeamAdapter;
import com.example.mateusz.fantasy.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.BUDGET_EXTRA;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.TEAM_ID_EXTRA;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.USER_ID_EXTRA;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment implements ParentFragment, RVTeamAdapter.TeamFragmentCallback, ITeamView {

    public static final String PLAYERS_TO_TRANSFER_EXTRA = "playersToTransferExtra";
    public static final String USERS_TEAM_EXTRA = "usersTeam";

    public FrameLayout fragmentContainer;
    private RecyclerView mRvTeam;
    private RVTeamAdapter rvTeamAdapter;
    private Button transferButton;
    private ProgressBar teamProgressBar;
    private ArrayList<Player> playersToTransfer;
    private ArrayList<Player> usersTeam;
    private int mUserId;
    private int mTeamId;
    private float mBudget;

    private TeamPresenter teamPresenter;

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
        getLoggedUserData();
        getTeamFromIntent();
        teamPresenter = new TeamPresenter(this);
        teamPresenter.getUserTeam(mTeamId);
        return view;
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            mRvTeam.setVisibility(View.INVISIBLE);
            teamProgressBar.setVisibility(View.VISIBLE);
        } else {
            mRvTeam.setVisibility(View.VISIBLE);
            teamProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void presentTeam(ArrayList<Player> usersTeam) {
        this.usersTeam = usersTeam;
        Collections.sort(usersTeam);
        if (usersTeam.size()==0){
            Intent intent = new Intent(getActivity(), TransferActivity.class);
            startActivity(intent);
        }
        initRVAdapter(usersTeam);
    }

    @Override
    public void onGetTeamFailure() {
        NetworkUtils.showConnectionErrorToast(getActivity());
        teamPresenter.getUserTeam(mTeamId);
    }

    @SuppressWarnings("unchecked")
    private void getTeamFromIntent() {
        Intent intent = getActivity().getIntent();
        usersTeam = (ArrayList<Player>) intent.getSerializableExtra(USERS_TEAM_EXTRA);
    }

    private void initViews(View view) {
        fragmentContainer = view.findViewById(R.id.team_fragment_container);
        transferButton = view.findViewById(R.id.btn_transfer);
        transferButton.setEnabled(false);
        teamProgressBar = view.findViewById(R.id.pb_team);
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

    void initRVAdapter(ArrayList<Player> players) {
        if (rvTeamAdapter == null) {
            rvTeamAdapter = new RVTeamAdapter(players, getContext(), this);
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

    public void onButtonClick() {
        Intent intent = new Intent(getContext(), TransferActivity.class);
        intent.putExtra(PLAYERS_TO_TRANSFER_EXTRA, playersToTransfer);
        intent.putExtra(USERS_TEAM_EXTRA, usersTeam);
        intent.putExtra(BUDGET_EXTRA,mBudget);
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

    private void getLoggedUserData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        mUserId = sharedPreferences.getInt(USER_ID_EXTRA, 0);
        mTeamId = sharedPreferences.getInt(TEAM_ID_EXTRA,0);
        mBudget = sharedPreferences.getFloat(BUDGET_EXTRA,0);
    }
}
