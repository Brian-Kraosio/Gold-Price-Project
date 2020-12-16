package id.putraprima.mygoldtracker.screen.wallet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class WalletViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public WalletViewModelFactory(Application application) {
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WalletViewModel.class)) {
            return (T) new WalletViewModel(application);
        }
        throw new IllegalArgumentException("Must be WalletViewModel");
    }
}
