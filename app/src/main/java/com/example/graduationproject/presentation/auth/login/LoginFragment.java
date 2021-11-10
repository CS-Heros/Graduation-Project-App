package com.example.graduationproject.presentation.auth.login;

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

import com.example.graduationproject.common.Utils;
import com.example.graduationproject.databinding.FragmentLoginBinding;
import com.example.graduationproject.presentation.main.MainActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        handleClicks();

    }

    private void handleClicks() {
        binding.registerFromLoginTv.setOnClickListener(v -> {
            NavDirections action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment();
            Navigation.findNavController(requireView()).navigate(action);
        });

        binding.loginBtn.setOnClickListener(v -> {
            String error = handleInputs();
            if (error.equals("")) {
                String email = binding.emailEt.getText().toString();
                String password = binding.passwordEt.getText().toString();
                viewModel.login(email, password);

                // navigate after login is success
                startActivity(new Intent(requireContext(), MainActivity.class));
                requireActivity().finish();

            } else {
                Utils.toastMy(requireContext(), error, false);
            }
        });
    }

    private String handleInputs() {
        if (binding.emailEt.getText().toString().isEmpty()) {
            return "Please enter an email!";
        } else if (binding.passwordEt.getText().toString().isEmpty()) {
            return "Please enter the password";
        }
        return "";
    }
}
