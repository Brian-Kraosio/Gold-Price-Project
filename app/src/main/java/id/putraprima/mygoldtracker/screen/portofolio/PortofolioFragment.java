package id.putraprima.mygoldtracker.screen.portofolio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import id.putraprima.mygoldtracker.R;
import id.putraprima.mygoldtracker.adapter.TabGoldAdapter;
import id.putraprima.mygoldtracker.databinding.FragmentPortofolioBinding;
import id.putraprima.mygoldtracker.model.Profile;

public class PortofolioFragment extends Fragment {

    FragmentPortofolioBinding binding;
    PortofolioViewModel viewModel;

    public PortofolioFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_portofolio, container, false);
        binding.viewpager.setAdapter(new TabGoldAdapter(getChildFragmentManager()));

        //Give the TabLayout the ViewPager
        binding.slidingTabs.setupWithViewPager(binding.viewpager);

//        call the view model
        PortofolioViewModelFactory portofolioViewModelFactory = new PortofolioViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, portofolioViewModelFactory).get(PortofolioViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.openOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile profile = viewModel.getProfileLiveData().getValue();
                assert profile != null;
                NavDirections action = PortofolioFragmentDirections.actionPorfolioFragmentToProfileFragment(profile);
                Navigation.findNavController(requireView()).navigate(action);
            }
        });
    }

}