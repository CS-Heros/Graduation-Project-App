package com.example.graduationproject.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.graduationproject.R;
import com.example.graduationproject.common.SharedPreferenceManger;
import com.example.graduationproject.databinding.ActivityMainBinding;
import com.example.graduationproject.presentation.auth.AuthActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Inject
    public SharedPreferenceManger sharedPreferenceManger;

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

        binding.appBar.backBtnIv.setOnClickListener(v -> {
            navController.navigateUp();
        });

        binding.appBar.logoutIv.setOnClickListener(v -> {
            sharedPreferenceManger.setHasLoggedIn(false);
            sharedPreferenceManger.setToken("");
            startActivity(new Intent(MainActivity.this, AuthActivity.class));
            finish();
        });
    }

    //TODO just for testing till the ui decides to use bottom nav or appbar
    private void observeDestinations() {
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.homeFragment:
                    binding.appBar.titleTv.setText("Home");
                    binding.appBar.gotoProfileIv.setVisibility(View.VISIBLE);
                    binding.appBar.backBtnIv.setVisibility(View.GONE);
                    binding.appBar.logoutIv.setVisibility(View.GONE);
                    break;

                case R.id.profileFragment:
                    binding.appBar.titleTv.setText("Profile");
                    binding.appBar.gotoProfileIv.setVisibility(View.GONE);
                    binding.appBar.backBtnIv.setVisibility(View.VISIBLE);
                    binding.appBar.logoutIv.setVisibility(View.VISIBLE);
                    break;

                case R.id.resultFragment:
                    binding.appBar.titleTv.setText("Result");
                    showBackBtnAndGoToProfileBtn();
                    break;

                case R.id.scanFragment:
                    binding.appBar.titleTv.setText("Scan");
                    showBackBtnAndGoToProfileBtn();
                    break;

                case R.id.editFragment:
                    binding.appBar.titleTv.setText("Edit");
                    binding.appBar.backBtnIv.setVisibility(View.VISIBLE);
                    binding.appBar.gotoProfileIv.setVisibility(View.GONE);
                    binding.appBar.logoutIv.setVisibility(View.GONE);
                    break;

                default:
                    showBackBtnAndGoToProfileBtn();
                    break;
            }
        });
    }

    private void showBackBtnAndGoToProfileBtn() {
        binding.appBar.gotoProfileIv.setVisibility(View.VISIBLE);
        binding.appBar.backBtnIv.setVisibility(View.VISIBLE);
        binding.appBar.logoutIv.setVisibility(View.GONE);
    }
}