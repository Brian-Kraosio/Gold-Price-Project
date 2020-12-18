package id.putraprima.mygoldtracker.screen.addwallet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AddWalletViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public AddWalletViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AddWalletViewModel.class)) {
            return (T) new AddWalletViewModel(application);
        }
        throw new IllegalArgumentException("Must Be AddWalletViewModel class");
    }
}
