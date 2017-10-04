package com.example.mateusz.fantasy.home.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.mateusz.fantasy.home.view.fragment.HomeFragment;
import com.example.mateusz.fantasy.home.view.fragment.LeagueFragment;
import com.example.mateusz.fantasy.home.view.fragment.TeamFragment;
import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.utils.ZoomOutPageTransformer;

public class HomeActivity extends AppCompatActivity /*,LeagueFragment.LeagueFragmentCallback*/ {

    private BottomNavigationView mBottomNavigationView;
    private ViewPager mViewPager;
    private boolean mDoubleBackToExitPressedOnce = false;



    MenuItem prevMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mViewPager = findViewById(R.id.viewpager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        mViewPager.addOnPageChangeListener(onPageChangeListener);

        setupViewPager(mViewPager);
        mViewPager.setCurrentItem(1);
        mViewPager.setOffscreenPageLimit(2);

    }

    @Override
    public void onBackPressed() {

        if (mDoubleBackToExitPressedOnce) {
            finishAffinity();
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

    /**
     * Method to instantiate ViewPager with fragments
     * @param viewPager viewPager to instantiate
     */
    private void setupViewPager(ViewPager viewPager) {

         LeagueFragment mLeagueFragment;
         HomeFragment mHomeFragment;
         TeamFragment mTeamFragment;

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        mHomeFragment = new HomeFragment();
        mLeagueFragment = new LeagueFragment();
        mTeamFragment = new TeamFragment();

        adapter.addFragment(mLeagueFragment);
        adapter.addFragment(mHomeFragment);
        adapter.addFragment(mTeamFragment);

        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
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

}