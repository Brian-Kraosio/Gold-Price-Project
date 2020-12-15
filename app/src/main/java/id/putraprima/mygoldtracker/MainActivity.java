package id.putraprima.mygoldtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import id.putraprima.mygoldtracker.adapter.TabGoldAdapter;
//import id.putraprima.mygoldtracker.databinding.ActivityMainBinding;
import id.putraprima.mygoldtracker.model.Profile;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


//    private ActivityMainBinding binding;
//    private MainActivityViewModel viewModel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
//        //get the viewPager and set it's PagerAdapater so that it can display items
////        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        binding.viewpager.setAdapter(new TabGoldAdapter(getSupportFragmentManager(),MainActivity.this));
//        //Give the TabLayout the ViewPager
////        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
//        binding.slidingTabs.setupWithViewPager(binding.viewpager);
////        call the view model
//        MainActivityViewModelFactory mainActivityViewModelFactory = new MainActivityViewModelFactory(this.getApplication());
//        viewModel = new ViewModelProvider(this, mainActivityViewModelFactory).get(MainActivityViewModel.class);
//        binding.setViewModel(viewModel);
//        binding.setLifecycleOwner(this);
//
//////        get live data
////        viewModel.getProfileLiveData().observe(this, new Observer<Profile>() {
////            @Override
////            public void onChanged(Profile profile) {
////                if(profile!=null){
////                    binding.
////                }
////            }
////        });
//    }
//
//
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
//            Toast.makeText(this, "Logout Selected", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.Theme_MyGoldTracker);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//    }
}