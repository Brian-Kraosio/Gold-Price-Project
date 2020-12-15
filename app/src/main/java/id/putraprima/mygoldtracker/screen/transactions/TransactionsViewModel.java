package id.putraprima.mygoldtracker.screen.transactions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.putraprima.mygoldtracker.api.BuyModel;
import id.putraprima.mygoldtracker.api.PriceModel;
import id.putraprima.mygoldtracker.api.PriceRepository;
import id.putraprima.mygoldtracker.api.TokopediaDatabase;

public class TransactionsViewModel extends ViewModel {
    private PriceRepository priceRepository;
    private MutableLiveData<TokopediaDatabase<PriceModel>> priceMutableLive = new MutableLiveData<>();
    private MutableLiveData<List<BuyModel>> appListMutableLiveData = new MutableLiveData<>();

    public TransactionsViewModel() {
        this.priceRepository = PriceRepository.getInstance();
        priceMutableLive = priceRepository.getPrice();
    }

    public LiveData<TokopediaDatabase<PriceModel>> getPrice(){
        return priceMutableLive;
    }

    public LiveData<List<BuyModel>> getPriceList(){
        return appListMutableLiveData;
    }

    public void setBuyListMutableLiveData(List<BuyModel> list){
        appListMutableLiveData.setValue(list);
    }
}
