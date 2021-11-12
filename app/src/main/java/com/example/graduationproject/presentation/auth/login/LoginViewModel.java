package com.example.graduationproject.presentation.auth.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;
import com.example.graduationproject.domian.model.auth.AuthResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final RepositoryImpl repository;

    private final MutableLiveData<AuthResponse> authDataMutableLiveData = new MutableLiveData<>();
    public LiveData<AuthResponse> authDataLiveData = authDataMutableLiveData;

    @Inject
    public LoginViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }


    public void login(String email, String password) {
        repository.login(email, password).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    authDataMutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                authDataMutableLiveData.setValue(new AuthResponse("", "Please Check Internet connection!", null));
            }
        });
    }

}
