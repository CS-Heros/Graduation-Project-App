package com.example.graduationproject.presentation.main;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.graduationproject.R;
import com.example.graduationproject.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.home_nav_host_fragment);
        navController = navHostFragment.getNavController();

        handleClicks();
        observeDestinations();

    }

    private void handleClicks() {
        binding.appBar.gotoProfileIv.setOnClickListener(v -> {
            navController.navigate(R.id.profileFragment);
        });

        binding.appBar.goToSettingsIv.setOnClickListener(v -> {
            navController.navigate(R.id.settingsFragment);
        });
    }

    private void observeDestinations() {
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.profileFragment:
                    binding.appBar.goToSettingsIv.setVisibility(View.VISIBLE);
                    binding.appBar.gotoProfileIv.setVisibility(View.GONE);
                    break;

                case R.id.settingsFragment:
                    binding.appBar.goToSettingsIv.setVisibility(View.GONE);
                    binding.appBar.gotoProfileIv.setVisibility(View.GONE);
                    break;

                default:
                    binding.appBar.goToSettingsIv.setVisibility(View.GONE);
                    binding.appBar.gotoProfileIv.setVisibility(View.VISIBLE);
                    break;

            }
        });
    }
}