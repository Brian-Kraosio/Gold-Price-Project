package id.putraprima.mygoldtracker.screen.wallet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.putraprima.mygoldtracker.R;
import id.putraprima.mygoldtracker.api.PriceModel;
import id.putraprima.mygoldtracker.api.TokopediaDatabase;
import id.putraprima.mygoldtracker.api.WalletProfitModel;
import id.putraprima.mygoldtracker.databinding.FragmentWalletBinding;
import id.putraprima.mygoldtracker.model.Wallet;
import id.putraprima.mygoldtracker.screen.portofolio.PortofolioFragmentDirections;

public class WalletFragment extends Fragment {

    FragmentWalletBinding binding;
    WalletViewModel viewModel;
    Wallet wallet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false);
        WalletViewModelFactory walletViewModelFactory = new WalletViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, walletViewModelFactory).get(WalletViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = PortofolioFragmentDirections.actionPorfolioFragmentToAddWalletFragment();
                Navigation.findNavController(requireView()).navigate(action);
            }
        });

//        viewModel.priceLiveData().observe(getViewLifecycleOwner(), new Observer<TokopediaDatabase<PriceModel>>() {
//            @Override
//            public void onChanged(TokopediaDatabase<PriceModel> priceModelTokopediaDatabase) {
//                if (priceModelTokopediaDatabase!=null){
//                    List<WalletProfitModel> walletProfitModelList = new ArrayList<>();
//                    walletProfitModelList.add(new WalletProfitModel(0.5f, priceModelTokopediaDatabase.getData().getSell_price()/2 , priceModelTokopediaDatabase.getData().getSell_price()/2-(wallet.getBuyPrice()/2)));
//                    walletProfitModelList.add(new WalletProfitModel(1f, priceModelTokopediaDatabase.getData().getSell_price()*1 , priceModelTokopediaDatabase.getData().getSell_price()-(wallet.getBuyPrice()*1)));
//                    walletProfitModelList.add(new WalletProfitModel(2f, priceModelTokopediaDatabase.getData().getSell_price()*2 , priceModelTokopediaDatabase.getData().getSell_price()*2-(wallet.getBuyPrice()*2)));
//                    walletProfitModelList.add(new WalletProfitModel(3f, priceModelTokopediaDatabase.getData().getSell_price()*3 , priceModelTokopediaDatabase.getData().getSell_price()*3-(wallet.getBuyPrice()*3)));
//                    walletProfitModelList.add(new WalletProfitModel(4f, priceModelTokopediaDatabase.getData().getSell_price()*4 , priceModelTokopediaDatabase.getData().getSell_price()*4-(wallet.getBuyPrice()*4)));
//                    walletProfitModelList.add(new WalletProfitModel(5f, priceModelTokopediaDatabase.getData().getSell_price()*5 , priceModelTokopediaDatabase.getData().getSell_price()*5-(wallet.getBuyPrice()*5)));
//                    walletProfitModelList.add(new WalletProfitModel(10f, priceModelTokopediaDatabase.getData().getSell_price()*10 , priceModelTokopediaDatabase.getData().getSell_price()*10-(wallet.getBuyPrice()*10)));
//                    walletProfitModelList.add(new WalletProfitModel(25f, priceModelTokopediaDatabase.getData().getSell_price()*25 , priceModelTokopediaDatabase.getData().getSell_price()*25-(wallet.getBuyPrice()*25)));
//                    walletProfitModelList.add(new WalletProfitModel(50f, priceModelTokopediaDatabase.getData().getSell_price()*50 , priceModelTokopediaDatabase.getData().getSell_price()*50-(wallet.getBuyPrice()*50)));
//                    walletProfitModelList.add(new WalletProfitModel(100f, priceModelTokopediaDatabase.getData().getSell_price()*100 , priceModelTokopediaDatabase.getData().getSell_price()*100-(wallet.getBuyPrice()*100)));
//                    viewModel.setWalletListMutableLiveData(walletProfitModelList);
//                }else{
//                    Toast.makeText(getContext(), "Fail to load data", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        setupRvWallet();
    }

    public void setupRvWallet(){
        RecyclerView recyclerView = binding.walletRecycler;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        WalletAdapter adapter = new WalletAdapter();
        recyclerView.setAdapter(adapter);
        viewModel.getWalletListLiveData().observe(getViewLifecycleOwner(), new Observer<List<WalletProfitModel>>() {
            @Override
            public void onChanged(List<WalletProfitModel> walletProfitModels) {
                if(walletProfitModels!=null){
                    adapter.setWalletList(walletProfitModels);
                }
            }
        });

    }
}