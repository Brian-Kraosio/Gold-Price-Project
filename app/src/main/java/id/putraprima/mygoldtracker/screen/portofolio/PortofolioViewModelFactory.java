package id.putraprima.mygoldtracker.screen.portofolio;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PortofolioViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public PortofolioViewModelFactory(Application application) {
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(PortofolioViewModel.class)){
            return (T) new PortofolioViewModel(application);
        }
        throw new IllegalArgumentException("Must be PortofolioViewModel Class");
    }
}
