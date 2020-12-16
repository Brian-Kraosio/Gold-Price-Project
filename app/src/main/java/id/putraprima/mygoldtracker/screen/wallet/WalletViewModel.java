package id.putraprima.mygoldtracker.screen.wallet;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.putraprima.mygoldtracker.model.Wallet;
import id.putraprima.mygoldtracker.repository.WalletRepository;

public class WalletViewModel extends ViewModel {
    private WalletRepository walletRepository;
    public LiveData<List<Wallet>> listLiveData = new MutableLiveData<>();

    public WalletViewModel(Application application) {
        super();
        walletRepository = new WalletRepository(application);
        listLiveData = walletRepository.getListWalletLiveData();
    }
}
