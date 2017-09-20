package com.example.mateusz.fantasy.Home.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mateusz.fantasy.Home.model.League;
import com.example.mateusz.fantasy.Home.presenter.LeaguePresenter;
import com.example.mateusz.fantasy.Home.view.Fragment.HomeFragment;
import com.example.mateusz.fantasy.Home.view.Fragment.LeagueFragment;
import com.example.mateusz.fantasy.Home.view.Fragment.TeamFragment;
import com.example.mateusz.fantasy.R;

public class HomeActivity extends AppCompatActivity implements IHomeView,LeagueFragment.LeagueFragmentCallback {

    /**
     * Dependencies
     */
    private LeaguePresenter leaguePresenter;

    private BottomNavigationView mBottomNavigationView;
    private ViewPager mViewPager;
    private boolean mDoubleBackToExitPressedOnce = false;

    /**
     * Fragments
     */
    private LeagueFragment mLeagueFragment;
    private HomeFragment mHomeFragment;
    private TeamFragment mTeamFragment;

    MenuItem prevMenuItem;

    @Override
    public void onLeagueRecyclerViewItemClicked(League league) {
        leaguePresenter.startLeagueDetailActivity(league);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (leaguePresenter == null) {
            leaguePresenter = new LeaguePresenter();
        }

        mViewPager = findViewById(R.id.viewpager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        mViewPager.addOnPageChangeListener(onPageChangeListener);

        setupViewPager(mViewPager);
        mViewPager.setCurrentItem(1);
        mViewPager.setOffscreenPageLimit(2);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onViewAttached(this,this);
    }

    @Override
    protected void onPause() {

        super.onPause();
        getPresenter().onViewDetached();

    }


    /**
     * BottomNavigationView listener
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_league:
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.action_home:
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.action_team:
                    mViewPager.setCurrentItem(2);
                    break;
            }
            return false;
        }
    };

    /**
     * ViewPagerChangeListener
     */
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (prevMenuItem != null) {
                prevMenuItem.setChecked(false);
            } else {
                mBottomNavigationView.getMenu().getItem(0).setChecked(false);
            }
            mBottomNavigationView.getMenu().getItem(position).setChecked(true);
            prevMenuItem = mBottomNavigationView.getMenu().getItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mHomeFragment = new HomeFragment();

        mLeagueFragment = new LeagueFragment();
        mLeagueFragment.setHomeActivityCallback(this);

        mTeamFragment = new TeamFragment();
        adapter.addFragment(mLeagueFragment);
        adapter.addFragment(mHomeFragment);
        adapter.addFragment(mTeamFragment);
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {

        if (mDoubleBackToExitPressedOnce) {
            finish();
            moveTaskToBack(true);
            return;
        }

        this.mDoubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.back_button_close_app_warning), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDoubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public void startLeagueDetailActivity(Intent intent) {
        startActivity(intent);
    }

    public LeaguePresenter getPresenter() {
        return leaguePresenter;
    }
}
