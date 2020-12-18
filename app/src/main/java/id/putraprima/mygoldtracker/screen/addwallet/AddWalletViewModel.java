package id.putraprima.mygoldtracker.screen.addwallet;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import id.putraprima.mygoldtracker.model.Wallet;
import id.putraprima.mygoldtracker.repository.WalletRepository;

public class AddWalletViewModel extends ViewModel {
    private WalletRepository walletRepository;
    private MutableLiveData<Wallet> walletMutableLiveData = new MutableLiveData<>();
    public LiveData<List<Wallet>> listLiveData = new MutableLiveData<>();
    public LiveData<Wallet> walletLiveData;

    public AddWalletViewModel(Application application) {
        super();
        walletRepository = new WalletRepository(application);
        listLiveData = walletRepository.getListWalletLiveData();
    }

    public void onBtnClicked(String buyPrice, String weight, String buyDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(buyDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Wallet wallet = new Wallet(Float.parseFloat(weight), Float.parseFloat(buyPrice), date);
        walletRepository.insert(wallet);
        walletMutableLiveData.setValue(wallet);

    }

    public LiveData<Wallet> getWalletLiveData() {
        return walletMutableLiveData;
    }

    public void resetWalletMutableLiveData(){
        walletMutableLiveData.setValue(null);
    }
}
