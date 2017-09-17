package com.example.mateusz.fantasy.Home.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.mateusz.fantasy.Home.view.Fragment.HomeFragment;
import com.example.mateusz.fantasy.Home.view.Fragment.LeagueFragment;
import com.example.mateusz.fantasy.Home.view.Fragment.TeamFragment;
import com.example.mateusz.fantasy.Login.view.LoginActivity;
import com.example.mateusz.fantasy.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mateusz.fantasy.Login.view.LoginActivity.USER_ID_EXTRA;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    private boolean doubleBackToExitPressedOnce = false;

    /**
     * Fragments
     */
    private LeagueFragment leagueFragment;
    private HomeFragment homeFragment;
    private TeamFragment teamFragment;

    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.viewpager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        viewPager.addOnPageChangeListener(onPageChangeListener);

        setupViewPager(viewPager);
        viewPager.setCurrentItem(1);
    }


    /**
     * BottomNavigationView listener
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_league:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.action_home:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.action_team:
                    viewPager.setCurrentItem(2);
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
                bottomNavigationView.getMenu().getItem(0).setChecked(false);
            }
            bottomNavigationView.getMenu().getItem(position).setChecked(true);
            prevMenuItem = bottomNavigationView.getMenu().getItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment = new HomeFragment();
        leagueFragment = new LeagueFragment();
        teamFragment = new TeamFragment();
        adapter.addFragment(leagueFragment);
        adapter.addFragment(homeFragment);
        adapter.addFragment(teamFragment);
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            finish();
            moveTaskToBack(true);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.back_button_close_app_warning), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
