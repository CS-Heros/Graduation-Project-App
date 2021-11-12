package com.example.graduationproject.presentation.main.settings;

import static com.example.graduationproject.common.Utils.setImageUsingGlide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.graduationproject.databinding.FragmentSettingsBinding;
import com.example.graduationproject.domian.model.user.User;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private SettingsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        handleClicks();
        observeData();

        binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
        viewModel.getUser();
    }

    private void handleClicks() {

    }

    private void observeData() {
        viewModel.userData.observe(getViewLifecycleOwner(), userResponse -> {
            binding.loadingPbView.loadingPb.setVisibility(View.GONE);
            binding.settingsGroup.setVisibility(View.VISIBLE);

            User user = userResponse.getData();
            setImageUsingGlide(binding.shapeableImageView, user.getAvatar());
            binding.nameEt.setText("" + user.getName());
            binding.emailEt.setText("" + user.getEmail());

        });
    }
}
