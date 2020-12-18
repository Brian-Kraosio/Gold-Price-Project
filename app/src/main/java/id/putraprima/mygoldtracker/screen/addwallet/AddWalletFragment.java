package id.putraprima.mygoldtracker.screen.addwallet;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import id.putraprima.mygoldtracker.R;
import id.putraprima.mygoldtracker.databinding.FragmentAddWalletBinding;
import id.putraprima.mygoldtracker.model.Wallet;

public class AddWalletFragment extends Fragment {

    FragmentAddWalletBinding binding;
    AddWalletViewModel viewModel;
    DatePickerDialog pickerDialog;

    public AddWalletFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_wallet, container, false);
        AddWalletViewModelFactory addWalletViewModelFactory = new AddWalletViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, addWalletViewModelFactory).get(AddWalletViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                pickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        binding.dateText.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, year, month, day);
                pickerDialog.show();
            }
        });

        viewModel.getWalletLiveData().observe(getViewLifecycleOwner(), new Observer<Wallet>() {
            @Override
            public void onChanged(Wallet wallet) {
                if (wallet != null){
                    NavDirections action = AddWalletFragmentDirections.actionAddWalletFragmentToPorfolioFragment();
                    Navigation.findNavController(requireView()).navigate(action);
                    viewModel.resetWalletMutableLiveData();
                }
            }
        });
    }
}