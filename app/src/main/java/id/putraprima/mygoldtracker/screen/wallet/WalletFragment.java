package id.putraprima.mygoldtracker.screen.wallet;

import android.graphics.Color;
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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.putraprima.mygoldtracker.R;
import id.putraprima.mygoldtracker.adapter.WalletAdapter;
import id.putraprima.mygoldtracker.api.HistoryModel;
import id.putraprima.mygoldtracker.api.PriceModel;
import id.putraprima.mygoldtracker.api.TokopediaDatabase;
import id.putraprima.mygoldtracker.databinding.FragmentWalletBinding;
import id.putraprima.mygoldtracker.databinding.ItemWalletBinding;
import id.putraprima.mygoldtracker.model.Wallet;
import id.putraprima.mygoldtracker.screen.portofolio.PortofolioFragmentDirections;

public class WalletFragment extends Fragment {

    FragmentWalletBinding binding;
    WalletViewModel viewModel;
    PriceModel priceModel;

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
        setupRvWallet();
//        configureChart();
    }



    public void setupRvWallet(){
        RecyclerView recyclerView = binding.walletRecycler;
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        WalletHistoryAdapter adapter = new WalletHistoryAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.listWalletLiveData.observe(getViewLifecycleOwner(), new Observer<List<Wallet>>() {
            @Override
            public void onChanged(List<Wallet> wallets) {
                adapter.setList(wallets);
            }
        });

        viewModel.priceLiveData().observe(getViewLifecycleOwner(), new Observer<TokopediaDatabase<PriceModel>>() {
            @Override
            public void onChanged(TokopediaDatabase<PriceModel> priceModelTokopediaDatabase) {
                adapter.setPriceModel(priceModelTokopediaDatabase.getData());
            }
        });

        viewModel.historyLiveData().observe(getViewLifecycleOwner(), new Observer<TokopediaDatabase<List<HistoryModel>>>() {
            @Override
            public void onChanged(TokopediaDatabase<List<HistoryModel>> listTokopediaDatabase) {
                setLineChart(listTokopediaDatabase.getData());
            }
        });

    }

    public void setLineChart(List<HistoryModel> historyModels){
        List<ILineDataSet> dataSets = new ArrayList<>();
        ArrayList<Entry> list = new ArrayList<>();
        LineDataSet price = new LineDataSet(list, "Price");
        binding.chart.getAxisLeft().setTextColor(Color.WHITE);
//        list.add(new Entry(0, 20));
        float i = 0;
        List<HistoryModel> historyModelsList = historyModels;
        for (HistoryModel p: historyModelsList){
            list.add(new Entry(i++, p.getBuy_price()));
        }
        price.setDrawCircleHole(true);
        price.setCircleRadius(4);
        price.setDrawValues(false);
        price.setLineWidth(3);
        price.setColor(Color.YELLOW);
        price.setCircleColor(Color.YELLOW);
        dataSets.add(price);
        price.setValues(list);
        LineData lineData = new LineData(dataSets);
        binding.chart.setData(lineData);
        binding.chart.invalidate();
    }

//    public void configureChart(){
//        Description desc = new Description();
//        desc.setText("Price History");
//        desc.setTextSize(25);
//        binding.chart.setDescription(desc);
//
//        XAxis xAxis = binding.chart.getXAxis();
//        xAxis.setValueFormatter(new ValueFormatter() {
//            private final SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
//            @Override
//            public String getFormattedValue(float value) {
//                long millis = (long) (value * 1000L);
//                return format.format(new Date(millis));
//            }
//        });

//    }
}