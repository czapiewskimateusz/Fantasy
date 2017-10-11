package com.example.mateusz.fantasy.home.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.mateusz.fantasy.home.view.fragment.HomeFragment;
import com.example.mateusz.fantasy.home.view.fragment.LeagueFragment;
import com.example.mateusz.fantasy.home.view.fragment.TeamFragment;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();

    private Fragment mCurrentFragment;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);

        fragments.clear();
        fragments.add(new LeagueFragment());
        fragments.add(new HomeFragment());
        fragments.add(new TeamFragment());
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {

        if (getCurrentFragment() != object){
            mCurrentFragment = (Fragment) object;
        }

        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }
}
