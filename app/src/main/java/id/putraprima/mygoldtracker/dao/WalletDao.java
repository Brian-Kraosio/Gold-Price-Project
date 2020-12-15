package id.putraprima.mygoldtracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.putraprima.mygoldtracker.model.Wallet;

@Dao
public interface WalletDao {

    @Insert
    void insert(Wallet wallet);

    @Query("SELECT * FROM wallet")
    LiveData<List<Wallet>> getAllWalletData();

    @Query("SELECT SUM(weight) AS totalWeight FROM wallet")
    LiveData<Float> getTotalWeight();

    @Query("SELECT SUM(weight) AS selectedTotalWeight FROM wallet WHERE weight =:selectedWeight")
    LiveData<Float> getTotalSelectedWeight(float selectedWeight);

    @Query("SELECT SUM(buyPrice) AS totalBuyPrice FROM wallet")
    LiveData<Float> getTotalBuyPrice();

}
