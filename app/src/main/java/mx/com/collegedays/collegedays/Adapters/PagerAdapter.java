package mx.com.collegedays.collegedays.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import mx.com.collegedays.collegedays.Fragments.ClasesFragment;
import mx.com.collegedays.collegedays.Fragments.DFragment;
import mx.com.collegedays.collegedays.Fragments.JFragment;
import mx.com.collegedays.collegedays.Fragments.LFragment;
import mx.com.collegedays.collegedays.Fragments.MFragment;
import mx.com.collegedays.collegedays.Fragments.MarFragment;
import mx.com.collegedays.collegedays.Fragments.NotasFragment;
import mx.com.collegedays.collegedays.Fragments.SFragment;
import mx.com.collegedays.collegedays.Fragments.VFragment;

/**
 * Created by gerar on 16/04/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LFragment();
            case 1:
                return new MarFragment();
            case 2:
                return new MFragment();
            case 3:
                return new JFragment();
            case 4:
                return new VFragment();
            case 5:
                return new SFragment();
            case 6:
                return new DFragment();
            case 7:
                return new NotasFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
