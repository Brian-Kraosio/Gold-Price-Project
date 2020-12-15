package id.putraprima.mygoldtracker.screen.buy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.putraprima.mygoldtracker.R;
import id.putraprima.mygoldtracker.adapter.WalletAdapter;
import id.putraprima.mygoldtracker.databinding.FragmentBuyBinding;
import id.putraprima.mygoldtracker.model.Wallet;

public class BuyFragment extends Fragment {

    FragmentBuyBinding binding;
    BuyViewModel viewModel;

    public BuyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_buy, container, false);
        BuyViewModelFactory buyViewModelFactory = new BuyViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this,buyViewModelFactory).get(BuyViewModel.class);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRvWallet();
    }

    public void setupRvWallet(){
        RecyclerView recyclerView = binding.BuyRv;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        WalletAdapter adapter = new WalletAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.listLiveData.observe(getViewLifecycleOwner(), new Observer<List<Wallet>>() {
            @Override
            public void onChanged(List<Wallet> wallets) {
                adapter.setList(wallets);
            }
        });
    }
}