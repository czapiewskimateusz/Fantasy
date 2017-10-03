package com.example.mateusz.fantasy.Home.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mateusz.fantasy.Home.model.League;
import com.example.mateusz.fantasy.Home.model.UserRank;
import com.example.mateusz.fantasy.Home.presenter.LeagueDetailPresenter;
import com.example.mateusz.fantasy.Home.presenter.RVLeagueDetailsAdapter;
import com.example.mateusz.fantasy.R;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.mateusz.fantasy.Authentication.Login.view.LoginActivity.PREFS_NAME;
import static com.example.mateusz.fantasy.Authentication.Login.view.LoginActivity.USER_ID_EXTRA;
import static com.example.mateusz.fantasy.Home.model.League.CODE;
import static com.example.mateusz.fantasy.Home.model.League.LEAGUE_ID;
import static com.example.mateusz.fantasy.Home.model.League.NAME;
import static com.example.mateusz.fantasy.Home.model.League.NUMBER_OF_PLAYERS;
import static com.example.mateusz.fantasy.Home.model.League.USER_POSITION;
import static com.example.mateusz.fantasy.Home.presenter.LeaguePresenter.LEAGUE_BUNDLE_EXTRA;
import static com.example.mateusz.fantasy.Utils.NetworkUtils.showConnectionErrorToast;

public class LeagueDetailActivity extends AppCompatActivity implements ILeagueDetailView {

    private LeagueDetailPresenter mLeagueDetailPresenter;

    private League mLeague;
    private int mUserId;

    @BindView(R.id.tv_league_name_detail)
    TextView mTvLeagueName;
    @BindView(R.id.tv_ranking_detail)
    TextView mTvRanking;
    @BindView(R.id.tv_code_detail)
    TextView mTvCode;
    @BindView(R.id.tv_number_of_users)
    TextView mTvNumberOfPlayers;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.pB_league_detail)
    ProgressBar mProgressBar;
    @BindView(R.id.rv_league_detail)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_detail);

        mLeagueDetailPresenter = new LeagueDetailPresenter(this);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME,0);
        mUserId = sharedPreferences.getInt(USER_ID_EXTRA,0);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        getLeagueFromIntent(intent);

        initializeView();
        initializeRecyclerView();

        mLeagueDetailPresenter.getUsersRank(mLeague.getLeagueId());

    }

    @Override
    public void showProgress(boolean show) {

        if (show){
            mProgressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void presentUsersRank(List<UserRank> usersRank) {

        RVLeagueDetailsAdapter rvLeagueDetailsAdapter = new RVLeagueDetailsAdapter(usersRank,mUserId);
        recyclerView.setAdapter(rvLeagueDetailsAdapter);

    }

    private void getLeagueFromIntent(Intent intent){

        Bundle bundle = intent.getBundleExtra(LEAGUE_BUNDLE_EXTRA);
        mLeague = new League(bundle.getString(NAME),bundle.getInt(USER_POSITION),bundle.getString(CODE),bundle.getInt(LEAGUE_ID),bundle.getInt(NUMBER_OF_PLAYERS));

    }

    @Override
    public void onConnectionError() {
        showConnectionErrorToast(this);
    }

    /**
     * Initialize view with data form intent
     */
    private void initializeView(){
        mTvLeagueName.setText(mLeague.getName());
        mTvRanking.setText(String.format(Locale.ENGLISH,"%d", mLeague.getRank()));
        mTvCode.setText(mLeague.getCode());
        mTvNumberOfPlayers.setText(String.format(Locale.ENGLISH,"%d", mLeague.getNumberOfPlayers()));

        mIvShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_league_text_pt1) + " \""+mTvLeagueName.getText().toString()+"\"" + getString(R.string.share_league_text_pt2) + mTvCode.getText().toString());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }

        });
    }

    /**
     * Initialize RecyclerView
     */
    private void initializeRecyclerView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }
}
