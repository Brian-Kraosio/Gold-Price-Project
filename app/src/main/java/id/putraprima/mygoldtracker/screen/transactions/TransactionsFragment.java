package id.putraprima.mygoldtracker.screen.transactions;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.putraprima.mygoldtracker.R;
import id.putraprima.mygoldtracker.api.BuyModel;
import id.putraprima.mygoldtracker.api.PriceModel;
import id.putraprima.mygoldtracker.api.TokopediaEnvelope;
import id.putraprima.mygoldtracker.databinding.FragmentTransactionsBinding;

public class TransactionsFragment extends Fragment {

    private FragmentTransactionsBinding binding;
    private TransactionsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transactions, container, false);
        viewModel = new ViewModelProvider(this).get(TransactionsViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getPrice().observe(getViewLifecycleOwner(), new Observer<TokopediaEnvelope<PriceModel>>() {
            @Override
            public void onChanged(TokopediaEnvelope<PriceModel> priceModelTokopediaDatabase) {
                if(priceModelTokopediaDatabase!=null){
                    List<BuyModel> priceList = new ArrayList<>();
                    priceList.add(new BuyModel(0.5f, priceModelTokopediaDatabase.getData().getSell_price()/2, priceModelTokopediaDatabase.getData().getBuy_price()/2));
                    priceList.add(new BuyModel(1f, priceModelTokopediaDatabase.getData().getSell_price(), priceModelTokopediaDatabase.getData().getBuy_price()));
                    priceList.add(new BuyModel(2f, priceModelTokopediaDatabase.getData().getSell_price()*2, priceModelTokopediaDatabase.getData().getBuy_price()*2));
                    priceList.add(new BuyModel(3f, priceModelTokopediaDatabase.getData().getSell_price()*3, priceModelTokopediaDatabase.getData().getBuy_price()*3));
                    priceList.add(new BuyModel(4f, priceModelTokopediaDatabase.getData().getSell_price()*4, priceModelTokopediaDatabase.getData().getBuy_price()*4));
                    priceList.add(new BuyModel(5f, priceModelTokopediaDatabase.getData().getSell_price()*5, priceModelTokopediaDatabase.getData().getBuy_price()*5));
                    priceList.add(new BuyModel(10f, priceModelTokopediaDatabase.getData().getSell_price()*10, priceModelTokopediaDatabase.getData().getBuy_price()*10));
                    priceList.add(new BuyModel(25f, priceModelTokopediaDatabase.getData().getSell_price()*25, priceModelTokopediaDatabase.getData().getBuy_price()*25));
                    priceList.add(new BuyModel(50f, priceModelTokopediaDatabase.getData().getSell_price()*50, priceModelTokopediaDatabase.getData().getBuy_price()*50));
                    priceList.add(new BuyModel(100f, priceModelTokopediaDatabase.getData().getSell_price()*100, priceModelTokopediaDatabase.getData().getBuy_price()*100));
                    viewModel.setBuyListMutableLiveData(priceList);
                }else{
                    Toast.makeText(getContext(), "Fail to Load API data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        setupRvPrice();
    }

    private void setupRvPrice(){
        RecyclerView recyclerView = binding.BuyRv;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        TransactionsAdapter adapter = new TransactionsAdapter();
        recyclerView.setAdapter(adapter);
        viewModel.getPriceList().observe(getViewLifecycleOwner(), new Observer<List<BuyModel>>() {
            @Override
            public void onChanged(List<BuyModel> buyModels) {
                if (buyModels!=null){
                    adapter.setList(buyModels);
                }
            }
        });
    }
}