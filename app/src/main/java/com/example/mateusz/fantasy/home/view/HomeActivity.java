package com.example.mateusz.fantasy.home.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.example.mateusz.fantasy.home.view.fragment.ParentFragment;
import com.example.mateusz.fantasy.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity /*,LeagueFragment.LeagueFragmentCallback*/ {

    private ParentFragment mCurrentFragment;
    private ViewPagerAdapter mViewPagerAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    //UI
    private AHBottomNavigationViewPager viewPager;
    private AHBottomNavigation bottomNavigation;

    private boolean mDoubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUi();
    }

    private void initUi() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.view_pager);
        addItemsToBottomNavigation(bottomNavigation);
        customizeBottomNavigation(bottomNavigation);
        setOnTabSelectedListenerToBottomNavigation(bottomNavigation);
        initViewPager();
        setDefaultPage();
    }

    private void setDefaultPage() {
        bottomNavigation.setCurrentItem(1);
        viewPager.setCurrentItem(1);
        mCurrentFragment = mViewPagerAdapter.getCurrentFragment();
    }

    private void initViewPager() {
        viewPager.setOffscreenPageLimit(3);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);
    }

    private void setOnTabSelectedListenerToBottomNavigation(AHBottomNavigation bottomNavigation) {
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (mCurrentFragment == null) mCurrentFragment = mViewPagerAdapter.getCurrentFragment();
                viewPager.setCurrentItem(position, false);
                if (mCurrentFragment == null) return true;
                if (mCurrentFragment != null) mCurrentFragment.willBeHidden();
                mCurrentFragment = mViewPagerAdapter.getCurrentFragment();
                mCurrentFragment.willBeDisplayed();
                return true;
            }
        });
    }

    private void customizeBottomNavigation(AHBottomNavigation bottomNavigation) {
        bottomNavigation.setAccentColor(fetchColor(R.color.primary));
        bottomNavigation.setInactiveColor(fetchColor(R.color.light_grey));
    }

    private void addItemsToBottomNavigation(AHBottomNavigation bottomNavigation) {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_league, R.drawable.ic_league, R.color.accent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_home, R.drawable.ic_home, R.color.accent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_team, R.drawable.ic_team, R.color.accent);
        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigation.addItems(bottomNavigationItems);
    }

//    @Override
//    public void onBackPressed() {
//        if (mDoubleBackToExitPressedOnce) {
//            finishAffinity();
//            return;
//        }
//        this.mDoubleBackToExitPressedOnce = true;
//        Toast.makeText(this, getString(R.string.back_button_close_app_warning), Toast.LENGTH_SHORT).show();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mDoubleBackToExitPressedOnce = false;
//            }
//        }, 2000);
//    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }

}
