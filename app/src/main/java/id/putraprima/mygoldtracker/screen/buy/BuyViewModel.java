package id.putraprima.mygoldtracker.screen.buy;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.putraprima.mygoldtracker.model.Wallet;
import id.putraprima.mygoldtracker.repository.WalletRepository;

public class BuyViewModel extends ViewModel {
    private WalletRepository walletRepository;
    public LiveData<List<Wallet>> listLiveData = new MutableLiveData<>();

    public BuyViewModel(Application application) {
        super();
        this.walletRepository = new WalletRepository(application);
        listLiveData = walletRepository.getListWalletLiveData();
    }
}
