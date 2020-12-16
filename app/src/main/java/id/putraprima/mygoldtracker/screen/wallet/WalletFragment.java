package id.putraprima.mygoldtracker.screen.wallet;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.putraprima.mygoldtracker.R;
import id.putraprima.mygoldtracker.databinding.FragmentWalletBinding;

public class WalletFragment extends Fragment {

    FragmentWalletBinding binding;
    WalletViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false);
        return binding.getRoot();
    }
}