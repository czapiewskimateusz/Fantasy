package com.example.mateusz.fantasy.leagues.view;

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

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.leagues.model.repo.League;
import com.example.mateusz.fantasy.leagues.model.repo.UserRank;
import com.example.mateusz.fantasy.leagues.presenter.LeagueDetailPresenter;
import com.example.mateusz.fantasy.leagues.presenter.LeaguePresenter;
import com.example.mateusz.fantasy.leagues.presenter.adapter.RVLeagueDetailsAdapter;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.PREFS_NAME;
import static com.example.mateusz.fantasy.authentication.login.view.LoginActivity.USER_ID_EXTRA;
import static com.example.mateusz.fantasy.leagues.model.repo.League.CODE;
import static com.example.mateusz.fantasy.leagues.model.repo.League.LEAGUE_ID;
import static com.example.mateusz.fantasy.leagues.model.repo.League.NAME;
import static com.example.mateusz.fantasy.leagues.model.repo.League.NUMBER_OF_PLAYERS;
import static com.example.mateusz.fantasy.leagues.model.repo.League.USER_POSITION;
import static com.example.mateusz.fantasy.utils.NetworkUtils.showConnectionErrorToast;

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
        ButterKnife.bind(this);

        mLeagueDetailPresenter = new LeagueDetailPresenter(this);
        getDataFromSharedPreferences();
        getLeagueFromIntent();
        initView();
        initRecyclerView();
        showProgress(true);
        mLeagueDetailPresenter.getUsersRank(mLeague.getLeagueId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress(false);
    }

    private void getDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME,0);
        mUserId = sharedPreferences.getInt(USER_ID_EXTRA,0);
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

    private void getLeagueFromIntent(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(LeaguePresenter.LEAGUE_BUNDLE_EXTRA);
        mLeague = new League(bundle.getString(NAME),bundle.getInt(USER_POSITION),bundle.getString(CODE),bundle.getInt(LEAGUE_ID),bundle.getInt(NUMBER_OF_PLAYERS));
    }

    @Override
    public void onConnectionError() {
        showConnectionErrorToast(this);
        mLeagueDetailPresenter.getUsersRank(mLeague.getLeagueId());
    }

    /**
     * Initialize view with data form intent
     */
    private void initView(){
        mTvLeagueName.setText(mLeague.getName());
        mTvRanking.setText(String.format(Locale.ENGLISH,"%d", mLeague.getRank()));
        mTvCode.setText(mLeague.getCode());
        mTvNumberOfPlayers.setText(String.format(Locale.ENGLISH,"%d", mLeague.getNumberOfPlayers()));
        initShareButton();
    }

    private void initShareButton() {
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
    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }
}
