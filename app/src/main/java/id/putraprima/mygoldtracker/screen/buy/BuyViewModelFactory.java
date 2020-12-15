package id.putraprima.mygoldtracker.screen.buy;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BuyViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public BuyViewModelFactory(Application application) {
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BuyViewModel.class)) {
            return (T) new BuyViewModel(application);
        }
        throw new IllegalArgumentException("Must be BuyViewModel Class");
    }
}
