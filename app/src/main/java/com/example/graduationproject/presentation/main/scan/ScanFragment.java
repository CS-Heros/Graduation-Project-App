package com.example.graduationproject.presentation.main.scan;


import static com.example.graduationproject.common.Utils.getImageAsMultiBodyPart;
import static com.example.graduationproject.common.Utils.pickImage;
import static com.example.graduationproject.common.Utils.toastMe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
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
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.graduationproject.common.SharedPreferenceManger;
import com.example.graduationproject.databinding.FragmentScanBinding;
import com.example.graduationproject.presentation.main.utils.OnImageUriSelected;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.MultipartBody;

@AndroidEntryPoint
public class ScanFragment extends Fragment implements OnImageUriSelected {

    private FragmentScanBinding binding;
    private ScanViewModel viewModel;
    private OnImageUriSelected listener;

    @Inject
    public SharedPreferenceManger sharedPreferenceManger;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentScanBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ScanViewModel.class);
        listener = this;
        handleClicks();
        observeData();
    }


    // TODO upload image must return with id
    private void observeData() {
        viewModel.diseaseImage.observe(getViewLifecycleOwner(), fakeListResponse -> {
            binding.loadingPbView.loadingPb.setVisibility(View.GONE);
            if (fakeListResponse.getError().isEmpty()) {
                NavDirections action = ScanFragmentDirections.actionScanFragmentToResultFragment(-1, fakeListResponse.getData().getDiseases().get(0).getImg());
                Navigation.findNavController(requireView()).navigate(action);
            } else {
                toastMe(requireContext(), fakeListResponse.getError(), false);
            }
        });
    }

    private void checkForCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // You can use the Camera
            pickImage(requireContext(), listener);
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
                    pickImage(requireContext(), listener);
                } else {
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
            });


    @Override
    public void onSelect(Uri uri) {
        binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
        MultipartBody.Part img = getImageAsMultiBodyPart(requireActivity(), uri, "img");
        viewModel.uploadPhoto(img);
    }
}