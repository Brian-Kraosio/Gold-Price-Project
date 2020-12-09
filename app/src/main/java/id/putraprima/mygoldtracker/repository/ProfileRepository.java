package id.putraprima.mygoldtracker.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import id.putraprima.mygoldtracker.dao.ProfileDao;
import id.putraprima.mygoldtracker.database.ProfileTable;
import id.putraprima.mygoldtracker.model.Profile;

public class ProfileRepository {
    private ProfileDao profileDao;
    private LiveData<Profile> profileLiveData;

    public ProfileRepository(Application application) {
        ProfileTable table = ProfileTable.getDatabaseProfileTable(application);
        profileDao = table.profileDao();
        profileLiveData = profileDao.getProfile();
    }

    public LiveData<Profile> getProfileLiveData() {
        return profileLiveData;
    }

    public void insert(Profile profile){
        new insertAsyncTask(profileDao).execute(profile);
    }

    private class insertAsyncTask extends AsyncTask<Profile, Void, Void>{
        private ProfileDao profileDao;

        public insertAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.insert(profiles[0]);
            return null;
        }
    }

    public void deleteAll(){
        new deleteAllAsyncTask(profileDao).execute();
    }

    private class deleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private ProfileDao profileDao;

        public deleteAllAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            profileDao.deleteAllData();
            return null;
        }
    }

    public void update(Profile profile){
        new updateAsyncTask(profileDao).execute(profile);
    }

    private class updateAsyncTask extends AsyncTask<Profile,Void,Void>{
        private ProfileDao profileDao;

        public updateAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.update(profiles[0]);
            return null;
        }
    }
}
