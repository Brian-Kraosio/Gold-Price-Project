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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.putraprima.mygoldtracker.R;
import id.putraprima.mygoldtracker.api.HistoryModel;
import id.putraprima.mygoldtracker.api.PriceModel;
import id.putraprima.mygoldtracker.api.TokopediaEnvelope;
import id.putraprima.mygoldtracker.databinding.FragmentWalletBinding;
import id.putraprima.mygoldtracker.model.Wallet;
import id.putraprima.mygoldtracker.screen.portofolio.PortofolioFragmentDirections;

public class WalletFragment extends Fragment {

    FragmentWalletBinding binding;
    WalletViewModel viewModel;

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
        viewModel.historyLiveData().observe(getViewLifecycleOwner(), new Observer<TokopediaEnvelope<List<HistoryModel>>>() {
            @Override
            public void onChanged(TokopediaEnvelope<List<HistoryModel>> listTokopediaDatabase) {
                setLineChart(listTokopediaDatabase.getData());
            }
        });
        configureChart();
        setupRvWallet();
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

        viewModel.priceLiveData().observe(getViewLifecycleOwner(), new Observer<TokopediaEnvelope<PriceModel>>() {
            @Override
            public void onChanged(TokopediaEnvelope<PriceModel> priceModelTokopediaDatabase) {
                adapter.setPriceModel(priceModelTokopediaDatabase.getData());
            }
        });

    }

    public void configureChart(){
        Description desc = new Description();
        desc.setText("Price History");
        desc.setTextSize(25);
        desc.setTextColor(Color.WHITE);
        binding.chart.setDescription(desc);

    }

    public void setLineChart(List<HistoryModel> historyModels)  {
        ArrayList<Entry> list = new ArrayList<>();
        ArrayList<Entry> listSell = new ArrayList<>();
        LineDataSet price = new LineDataSet(list, "Buy Price");
        LineDataSet sellPrice = new LineDataSet(listSell, "Sell Price");
        binding.chart.getAxisLeft().setTextColor(Color.WHITE);
        binding.chart.getXAxis().setTextColor(Color.WHITE);
        binding.chart.getXAxis().setTextSize(9);
        binding.chart.getAxisLeft().setTextSize(9);

        float i = 0;
        List<HistoryModel> historyModelsList = historyModels;

        for (HistoryModel p: historyModelsList){
            list.add(new Entry(i++, p.getBuy_price()));
            listSell.add(new Entry(i, p.getSell_price()));
        }


        XAxis xAxis = binding.chart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            private final SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
            @Override
            public String getFormattedValue(float value) {
                return format.format(historyModelsList.get((int) value).getDate_price());
            }
        });

        YAxis yAxis = binding.chart.getAxisLeft();
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Locale myIndonesianLocale = new Locale("in", "ID");
                DecimalFormat format = (DecimalFormat) NumberFormat.getCurrencyInstance(myIndonesianLocale);
                format.setPositivePrefix("Rp. ");
                format.setNegativePrefix("Rp. -");
                return (format.format(value));
            }
        });


        price.setDrawCircleHole(true);
        price.setCircleRadius(2);
        price.setDrawValues(false);
        price.setLineWidth(1);
        price.setColor(Color.YELLOW);
        price.setCircleColor(Color.YELLOW);
        price.setDrawFilled(true);
        price.setFillColor(Color.YELLOW);

        sellPrice.setDrawCircleHole(true);
        sellPrice.setCircleRadius(2);
        sellPrice.setDrawValues(false);
        sellPrice.setLineWidth(1);
        sellPrice.setColor(Color.RED);
        sellPrice.setCircleColor(Color.RED);
        sellPrice.setDrawFilled(true);
        sellPrice.setFillColor(Color.RED);

        Legend legend = binding.chart.getLegend();
        legend.setEnabled(true);
        legend.setTextColor(Color.WHITE);
        binding.chart.setAutoScaleMinMaxEnabled(true);

        price.setValues(list);
        sellPrice.setValues(listSell);

        LineData lineData = new LineData(price, sellPrice);
        binding.chart.setData(lineData);
        binding.chart.invalidate();

    }
}