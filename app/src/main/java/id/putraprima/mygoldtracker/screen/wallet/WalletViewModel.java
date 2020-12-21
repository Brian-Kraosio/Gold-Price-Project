package id.putraprima.mygoldtracker.screen.wallet;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.putraprima.mygoldtracker.api.HistoryModel;
import id.putraprima.mygoldtracker.api.HistoryRepository;
import id.putraprima.mygoldtracker.api.PriceModel;
import id.putraprima.mygoldtracker.api.PriceRepository;
import id.putraprima.mygoldtracker.api.TokopediaEnvelope;
import id.putraprima.mygoldtracker.model.Wallet;
import id.putraprima.mygoldtracker.repository.WalletRepository;

public class WalletViewModel extends ViewModel {
    private WalletRepository walletRepository;
    private PriceRepository priceRepository;
    private HistoryRepository historyRepository;
    public LiveData<Float> weightLiveData = new MutableLiveData<>();
    private MutableLiveData<TokopediaEnvelope<PriceModel>> tokopediaDatabaseMutableLiveData = new MutableLiveData<>();
    public LiveData<List<Wallet>> listWalletLiveData = new MutableLiveData<>();
    private LiveData<Float> totalBuyPrice;
    private MutableLiveData<TokopediaEnvelope<List<HistoryModel>>> tokopediaHistoryMutableLiveData = new MutableLiveData<>();

    public WalletViewModel(Application application) {
        super();
        walletRepository = new WalletRepository(application);
        weightLiveData = walletRepository.getTotalWeightLiveData();
        priceRepository = PriceRepository.getInstance();

        tokopediaDatabaseMutableLiveData = priceRepository.getPrice();
        totalBuyPrice = walletRepository.getTotalBuyPriceLiveData();
        listWalletLiveData = walletRepository.getListWalletLiveData();

        historyRepository = HistoryRepository.getInstance();
        tokopediaHistoryMutableLiveData = historyRepository.getHistory();
    }

    public LiveData<TokopediaEnvelope<PriceModel>> priceLiveData(){
        return tokopediaDatabaseMutableLiveData;
    }

    public LiveData<TokopediaEnvelope<List<HistoryModel>>> historyLiveData(){
        return tokopediaHistoryMutableLiveData;
    }

    public LiveData<Float> getTotalBuyPrice(){
        return totalBuyPrice;
    }
}
