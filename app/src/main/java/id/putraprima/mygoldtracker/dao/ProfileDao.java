package id.putraprima.mygoldtracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import id.putraprima.mygoldtracker.model.Profile;

@Dao
public interface ProfileDao {
    @Insert
    void insert(Profile profile);

    @Update
    void update(Profile profile);

    @Query("DELETE FROM profile")
    void deleteAllData();

    @Query("SELECT * FROM profile LIMIT 1")
    LiveData<Profile> getProfile();

}
