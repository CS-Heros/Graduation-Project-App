package com.example.graduationproject.presentation.main.edit;

import static com.example.graduationproject.common.Utils.toastMe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.graduationproject.common.EditType;
import com.example.graduationproject.databinding.FragmentEditBinding;
import com.example.graduationproject.domian.model.user.User;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditFragment extends Fragment {

    private FragmentEditBinding binding;
    private EditViewModel viewModel;
    private EditType type;
    private User user;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(EditViewModel.class);
        type = EditFragmentArgs.fromBundle(getArguments()).getEditType();
        handleClicks();
        observeData();

        binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
        viewModel.getUser();
    }

    private void handleClicks() {
        binding.applyBtn.setOnClickListener(v -> {
            handleInputs(type);
        });
    }

    private void observeData() {
        viewModel.userData.observe(getViewLifecycleOwner(), userResponse -> {
            binding.loadingPbView.loadingPb.setVisibility(View.GONE);

            if (userResponse.getError().isEmpty()) {
                user = userResponse.getData();
                checkType(type, user);

            } else {
                toastMe(requireContext(), userResponse.getError(), false);
            }
        });

        viewModel.updatedUserData.observe(getViewLifecycleOwner(), userResponse -> {
            binding.loadingPbView.loadingPb.setVisibility(View.GONE);
            if (userResponse.getError().isEmpty()) {
                toastMe(requireContext(), "Updated Successfully!", true);
                Navigation.findNavController(requireView()).navigateUp();
            } else {
                toastMe(requireContext(), userResponse.getError(), false);
            }

        });

        viewModel.updatePassword.observe(getViewLifecycleOwner(), passwordResponse -> {
            binding.loadingPbView.loadingPb.setVisibility(View.GONE);
            if (passwordResponse.getError().isEmpty()) {
                toastMe(requireContext(), passwordResponse.getData().getMsg(), true);
                //go back
                Navigation.findNavController(requireView()).navigateUp();
            } else {
                toastMe(requireContext(), passwordResponse.getError(), false);
            }
        });
    }

    private void checkType(EditType type, User user) {
        switch (type) {
            case NAME:
                binding.nameGroup.setVisibility(View.VISIBLE);
                binding.nameEt.setText("" + user.getName());
                break;
            case EMAIL:
                binding.emailGroup.setVisibility(View.VISIBLE);
                binding.emailEt.setText("" + user.getEmail());
                break;
            case PASSWORD:
                binding.passwordGroup.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void handleInputs(EditType type) {
        switch (type) {
            case NAME:
                if (binding.nameEt.getText().toString().isEmpty()) {
                    toastMe(requireContext(), "Please enter a name", false);
                } else {
                    user.setName(binding.nameEt.getText().toString());
                    binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
                    viewModel.updateUserData(user);
                }
                break;
            case EMAIL:
                if (binding.emailEt.getText().toString().isEmpty()) {
                    toastMe(requireContext(), "Please enter an email!", false);
                } else {
                    user.setEmail(binding.emailEt.getText().toString());
                    binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
                    viewModel.updateUserData(user);
                }
                break;
            case PASSWORD:
                if (binding.oldPasswordEt.getText().toString().isEmpty()) {
                    toastMe(requireContext(), "Please enter old password", false);
                } else if (binding.newPasswordEt.getText().toString().isEmpty()) {
                    toastMe(requireContext(), "Please enter the new password", false);
                } else if (!binding.newPasswordEt.getText().toString().equals(binding.confirmPasswordEt.getText().toString())) {
                    toastMe(requireContext(), "The password don't match confirm password", false);
                } else {
                    binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
                    viewModel.updateUserPassword(binding.oldPasswordEt.getText().toString(), binding.newPasswordEt.getText().toString());
                }
                break;
        }
    }
}
