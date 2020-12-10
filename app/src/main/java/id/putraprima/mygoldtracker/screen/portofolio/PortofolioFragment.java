package id.putraprima.mygoldtracker.screen.portofolio;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import id.putraprima.mygoldtracker.R;
import id.putraprima.mygoldtracker.adapter.TabGoldAdapter;
import id.putraprima.mygoldtracker.databinding.FragmentPortofolioBinding;

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
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.logout_settings, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch(item.getItemId()){
//            case R.id.logout:
//                Toast.makeText(this, "Logout Selected", Toast.LENGTH_SHORT).show();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}