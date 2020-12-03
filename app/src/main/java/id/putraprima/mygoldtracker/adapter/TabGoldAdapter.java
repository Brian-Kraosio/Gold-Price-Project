package id.putraprima.mygoldtracker.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import id.putraprima.mygoldtracker.screen.portolio.PorfolioFragment;

public class TabGoldAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] {"Wallet","Transactions","Buy/Sell"};
    private Context context;

    public TabGoldAdapter(FragmentManager fm, Context context){
        super(fm); this.context = context;
    }

    @Override
    public int getCount(){
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position){
        return PorfolioFragment.newInstance(position+1);
    }

    @Override public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }
}
