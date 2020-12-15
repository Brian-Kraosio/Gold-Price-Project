package id.putraprima.mygoldtracker.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.putraprima.mygoldtracker.dao.WalletDao;
import id.putraprima.mygoldtracker.database.ProfileTable;
import id.putraprima.mygoldtracker.model.Wallet;

public class WalletRepository {
    private WalletDao walletDao;
    private LiveData<Wallet> walletLiveData;
    private LiveData<List<Wallet>> listWalletLiveData;
    private LiveData<Float> totalWeightLiveData;
    private LiveData<Float> totalBuyPriceLiveData;

    public WalletRepository(Application application) {
        ProfileTable db = ProfileTable.getDatabaseProfileTable(application);
        walletDao = db.walletDao();
        listWalletLiveData = walletDao.getAllWalletData();
    }

    public LiveData<Float> getTotalBuyPriceLiveData() {
        totalBuyPriceLiveData = walletDao.getTotalBuyPrice();
        return totalBuyPriceLiveData;
    }

    public LiveData<Float> getTotalWeightLiveData() {
        totalWeightLiveData = walletDao.getTotalWeight();
        return totalWeightLiveData;
    }

    public LiveData<List<Wallet>> getListWalletLiveData() {
        return listWalletLiveData;
    }

    public void insert(Wallet wallet){
        new WalletRepository.insertAsyncTask(walletDao).execute(wallet);
    }

    private class insertAsyncTask extends AsyncTask<Wallet,Void,Void>{
        private WalletDao walletDao;

        public insertAsyncTask(WalletDao walletDao) {
            this.walletDao = walletDao;
        }

        @Override
        protected Void doInBackground(Wallet... wallets) {
            walletDao.insert(wallets[0]);
            return null;
        }
    }
}
