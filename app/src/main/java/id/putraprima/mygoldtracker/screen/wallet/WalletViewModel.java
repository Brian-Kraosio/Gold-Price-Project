package id.putraprima.mygoldtracker.screen.wallet;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.putraprima.mygoldtracker.api.BuyModel;
import id.putraprima.mygoldtracker.api.PriceModel;
import id.putraprima.mygoldtracker.api.PriceRepository;
import id.putraprima.mygoldtracker.api.TokopediaDatabase;
import id.putraprima.mygoldtracker.api.WalletProfitModel;
import id.putraprima.mygoldtracker.model.Wallet;
import id.putraprima.mygoldtracker.repository.WalletRepository;

public class WalletViewModel extends ViewModel {
    private WalletRepository walletRepository;
    private PriceRepository priceRepository;
    public LiveData<Float> weightLiveData = new MutableLiveData<>();
    private MutableLiveData<TokopediaDatabase<PriceModel>> tokopediaDatabaseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<WalletProfitModel>> walletListMutableLiveData = new MutableLiveData<>();
    public LiveData<List<Wallet>> listWalletLiveData;
    private LiveData<Float> totalBuyPrice;

    public WalletViewModel(Application application) {
        super();
        walletRepository = new WalletRepository(application);
        weightLiveData = walletRepository.getTotalWeightLiveData();
        priceRepository = PriceRepository.getInstance();
        tokopediaDatabaseMutableLiveData = priceRepository.getPrice();
        totalBuyPrice = walletRepository.getTotalBuyPriceLiveData();
        listWalletLiveData = walletRepository.getListWalletLiveData();
    }

    public LiveData<TokopediaDatabase<PriceModel>> priceLiveData(){
        return tokopediaDatabaseMutableLiveData;
    }

    public LiveData<List<WalletProfitModel>> getWalletListLiveData(){
        return walletListMutableLiveData;
    }

    public void setWalletListMutableLiveData(List<WalletProfitModel> list) {
        walletListMutableLiveData.setValue(list);
    }

    public LiveData<Float> getTotalBuyPrice(){
        return totalBuyPrice;
    }
}
