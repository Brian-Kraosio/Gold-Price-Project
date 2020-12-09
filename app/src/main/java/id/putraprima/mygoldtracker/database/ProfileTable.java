package id.putraprima.mygoldtracker.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

import id.putraprima.mygoldtracker.dao.ProfileDao;
import id.putraprima.mygoldtracker.model.Profile;

@Database(entities = {Profile.class}, version = 1, exportSchema = false)
public abstract class ProfileTable extends RoomDatabase {
    public abstract ProfileDao profileDao();
    private static ProfileTable INSTANCE;
    private static Context context;

    public static ProfileTable getDatabaseProfileTable(Context context){
    if (INSTANCE == null){
         context = context;
         synchronized (ProfileTable.class){
             if (INSTANCE == null){
                 INSTANCE = Room.databaseBuilder(context.getApplicationContext(),ProfileTable.class, "Profile")
                         .fallbackToDestructiveMigration()
                         .addCallback(callback)
                         .build();
             }
         }
    }
    return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    getDatabaseProfileTable(context).profileDao().insert(new Profile("Jacob Voyle", "jacobvoyle@gmail.com", ""));
                }
            });
        }
    };

}
