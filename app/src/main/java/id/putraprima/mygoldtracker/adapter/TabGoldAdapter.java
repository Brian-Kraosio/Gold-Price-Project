package id.putraprima.mygoldtracker.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import id.putraprima.mygoldtracker.screen.buy.BuyFragment;
import id.putraprima.mygoldtracker.screen.transactions.TransactionsFragment;
import id.putraprima.mygoldtracker.screen.wallet.WalletFragment;

public class TabGoldAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] {"Wallet","Price","Buy"};
    private Context context;

    public TabGoldAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public int getCount(){
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new WalletFragment();
                break;
            case 1:
                fragment = new TransactionsFragment();
                break;
            case 2 :
                fragment = new BuyFragment();
        }
        return fragment;
    }

    @Override public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }
}
