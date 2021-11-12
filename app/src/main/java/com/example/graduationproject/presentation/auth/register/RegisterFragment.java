package com.example.graduationproject.presentation.auth.register;

import static com.example.graduationproject.common.Utils.toastMe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.graduationproject.common.SharedPreferenceManger;
import com.example.graduationproject.common.Utils;
import com.example.graduationproject.databinding.FragmentRegisterBinding;
import com.example.graduationproject.presentation.main.MainActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Inject
    public SharedPreferenceManger sharedPreferenceManger;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleClicks();
        observeData();
    }

    private void handleClicks() {
        binding.registerFromLoginTv.setOnClickListener(v -> {
            NavDirections action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment();
            Navigation.findNavController(requireView()).navigate(action);
        });

        binding.registerBtn.setOnClickListener(v -> {
            String error = handleInputs();
            if (error.equals("")) {
                String name = binding.nameEt.getText().toString();
                String email = binding.emailEt.getText().toString();
                String password = binding.passwordEt.getText().toString();

                binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
                viewModel.register(name, email, password);

            } else {
                Utils.toastMe(requireContext(), error, false);
            }
        });
    }

    private String handleInputs() {
        if (binding.nameEt.getText().toString().isEmpty()) {
            return "Please enter a name";
        } else if (binding.emailEt.getText().toString().isEmpty()) {
            return "Please enter an email!";
        } else if (binding.passwordEt.getText().toString().isEmpty()) {
            return "Please enter the password";
        } else if (!binding.passwordEt.getText().toString().equals(binding.confirmPasswordEt.getText().toString())) {
            return "The password don't match confirm password";
        }
        return "";
    }

    private void observeData() {
        viewModel.authDataLiveData.observe(getViewLifecycleOwner(), authResponse -> {
            binding.loadingPbView.loadingPb.setVisibility(View.GONE);
            String error = authResponse.getError();
            if (error.isEmpty()) {
                // success
                String token = authResponse.getData().getToken();

                sharedPreferenceManger.setHasLoggedIn(true);
                sharedPreferenceManger.setToken(token);
                // navigate after login is success
                startActivity(new Intent(requireContext(), MainActivity.class));
                requireActivity().finish();

            } else {
                // fail
                toastMe(requireContext(), error, false);
            }
        });
    }
}
