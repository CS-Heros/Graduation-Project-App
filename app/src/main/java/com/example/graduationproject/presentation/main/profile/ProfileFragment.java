package com.example.graduationproject.presentation.main.profile;

import static com.example.graduationproject.common.Utils.getImageAsMultiBodyPart;
import static com.example.graduationproject.common.Utils.pickImage;
import static com.example.graduationproject.common.Utils.setImageUsingGlide;
import static com.example.graduationproject.common.Utils.toastMe;

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
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.graduationproject.common.EditType;
import com.example.graduationproject.databinding.FragmentProfileBinding;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListItem;
import com.example.graduationproject.domian.model.user.User;
import com.example.graduationproject.presentation.adapter.home_adapter.HomeCircularAdapter;
import com.example.graduationproject.presentation.main.utils.OnImageUriSelected;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.MultipartBody;

@AndroidEntryPoint
public class ProfileFragment extends Fragment implements OnImageUriSelected {

    private FragmentProfileBinding binding;
    private ProfileViewModel viewModel;
    private OnImageUriSelected listener;
    private User user;
    private HomeCircularAdapter profileAdapter = new HomeCircularAdapter();
    private List<FakeListItem> fakeListItems = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        listener = this;
        handleClicks();
        observeData();

        binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
        viewModel.getUser();
    }

    private void observeData() {
        viewModel.userData.observe(getViewLifecycleOwner(), userResponse -> {
            binding.loadingPbView.loadingPb.setVisibility(View.GONE);

            if (userResponse.getError().isEmpty()) {
                binding.profileGroup.setVisibility(View.VISIBLE);

                user = userResponse.getData();
                setImageUsingGlide(binding.profileImageIv, user.getAvatar());

                boolean isChecked = user.getAllowToSaveImage().equals("1");
                binding.saveImageSw.setChecked(isChecked);
                binding.nameEt.setText("" + user.getName());
                binding.emailEt.setText("" + user.getEmail());

            } else {
                toastMe(requireContext(), userResponse.getError(), false);
            }
        });


        viewModel.userImageData.observe(getViewLifecycleOwner(), userImageResponse -> {
            binding.loadingPbView.loadingPb.setVisibility(View.GONE);
            if (userImageResponse.getError().isEmpty()) {
                setImageUsingGlide(binding.profileImageIv, userImageResponse.getData().getImg());
            } else {
                toastMe(requireContext(), userImageResponse.getError(), false);
            }
        });

        viewModel.updatedUserData.observe(getViewLifecycleOwner(), userResponse -> {
            if (!userResponse.getError().isEmpty()) {
                toastMe(requireContext(), userResponse.getError(), false);
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
        binding.changeImageIv.setOnClickListener(view -> {
            checkForCameraPermission();
        });

        binding.nameEt.setOnClickListener(v -> {
            NavDirections action = ProfileFragmentDirections.actionProfileFragmentToEditFragment(EditType.NAME);
            Navigation.findNavController(requireView()).navigate(action);
        });

        binding.emailEt.setOnClickListener(v -> {
            NavDirections action = ProfileFragmentDirections.actionProfileFragmentToEditFragment(EditType.EMAIL);
            Navigation.findNavController(requireView()).navigate(action);
        });

        binding.editPassword.setOnClickListener(v -> {
            NavDirections action = ProfileFragmentDirections.actionProfileFragmentToEditFragment(EditType.PASSWORD);
            Navigation.findNavController(requireView()).navigate(action);
        });

        binding.saveImageSw.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            user.setAllowToSaveImage(isChecked ? "1" : "0");
            viewModel.updateUserData(user);
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
        Log.e("TAG", "onSelect: " + uri.toString());
        MultipartBody.Part img = getImageAsMultiBodyPart(requireActivity(), uri, "img");
        binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
        viewModel.updateUserImage(img);
    }
}
