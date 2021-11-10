package com.example.graduationproject.presentation.main.home;


import static com.example.graduationproject.common.Constants.IMAGE_MULTI_PART_NAME;
import static com.example.graduationproject.common.Utils.getImageAsMultiBodyPart;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.graduationproject.R;
import com.example.graduationproject.common.SharedPreferenceManger;
import com.example.graduationproject.common.Utils;
import com.example.graduationproject.databinding.FragmentHomeBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import gun0912.tedimagepicker.builder.TedImagePicker;
import okhttp3.MultipartBody;

@AndroidEntryPoint
public class HomeFragment extends Fragment implements OnImageUriSelected {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private OnImageUriSelected listener;

    @Inject
    public SharedPreferenceManger sharedPreferenceManger;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        listener = this;
        handleClicks();

        viewModel.login();

        viewModel.loginDataLiveData.observe(getViewLifecycleOwner(), loginResponse -> {
            sharedPreferenceManger.setToken(loginResponse.getData().getToken());
            sharedPreferenceManger.setHasLoggedIn(true);
            Toast.makeText(requireContext(), "token is: " + sharedPreferenceManger.getToken(), Toast.LENGTH_LONG).show();

        });

        Toast.makeText(requireContext(), "token is: " + sharedPreferenceManger.getToken(), Toast.LENGTH_LONG).show();

    }

    private void checkForCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // You can use the Camera
            pickImage();
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    private void handleClicks() {

        binding.openGalleryBtn.setOnClickListener(view -> {
            checkForCameraPermission();
        });
    }

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your app
                    pickImage();
                } else {
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
            });


    private void pickImage() {
        TedImagePicker.with(requireContext())
                .title("Choose image")
                .backButton(R.drawable.ic_arrow_back_black_24dp)
                .showCameraTile(true)
                .buttonBackground(R.drawable.btn_done_button)
                .buttonTextColor(R.color.white)
                .buttonText("Choose image")
                .errorListener(throwable -> {
                    Log.e("TAG", "pickImage: error " + throwable.getLocalizedMessage());
                })
                .start(uri -> {
                    Log.e("TAG", "pickImage: " + uri.toString());
                    listener.onSelect(uri);
                });

    }


    @Override
    public void onSelect(Uri uri) {
        Utils.setImageUsingGlide(binding.selectedImageIv, uri.toString());
        MultipartBody.Part image = getImageAsMultiBodyPart(requireContext(), uri, IMAGE_MULTI_PART_NAME);
        Log.e("TAG", "onSelect: " + image.toString());
    }
}