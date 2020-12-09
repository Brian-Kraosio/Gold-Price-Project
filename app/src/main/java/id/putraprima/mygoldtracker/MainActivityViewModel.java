package id.putraprima.mygoldtracker;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.putraprima.mygoldtracker.model.Profile;
import id.putraprima.mygoldtracker.repository.ProfileRepository;

public class MainActivityViewModel extends ViewModel {
    private ProfileRepository profileRepository;
    private LiveData<Profile> profileLiveData;

    public MainActivityViewModel(Application application) {
        super();
       profileRepository = new ProfileRepository(application);
       profileLiveData = profileRepository.getProfileLiveData();
    }

    public LiveData<Profile> getProfileLiveData() {
        return profileLiveData;
    }

    public void insert(Profile profile){
        profileRepository.insert(profile);
    }

    public void update(Profile profile){
        profileRepository.update(profile);
    }
}
