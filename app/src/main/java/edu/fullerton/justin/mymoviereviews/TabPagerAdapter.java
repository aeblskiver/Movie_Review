package edu.fullerton.justin.mymoviereviews;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import edu.fullerton.justin.mymoviereviews.view.MostRecentListFragment;
import edu.fullerton.justin.mymoviereviews.view.TopRatedListFragment;

/**
 * Created by justin on 11/26/17.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    public static final int NUMBER_OF_TABS = 2;

    public static final String[] TAB_NAMES = { "Most Recent", "Top Rated" };

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_NAMES[position];
    }

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_TABS;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MostRecentListFragment();
            case 1:
                return new TopRatedListFragment();
        }
        return null;
    }

}
