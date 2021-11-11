package com.example.graduationproject.presentation.auth.login;

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
import com.example.graduationproject.databinding.FragmentLoginBinding;
import com.example.graduationproject.presentation.main.MainActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    @Inject
    public SharedPreferenceManger sharedPreferenceManger;

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
        observeData();

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
                binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
                viewModel.login(email, password);

            } else {
                toastMe(requireContext(), error, false);
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
