package com.example.graduationproject.presentation.main.edit;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;
import com.example.graduationproject.domian.model.update_password.PasswordResponse;
import com.example.graduationproject.domian.model.user.User;
import com.example.graduationproject.domian.model.user.UserResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class EditViewModel extends ViewModel {

    private final RepositoryImpl repository;

    private final MutableLiveData<UserResponse> _userData = new MutableLiveData<>();
    public LiveData<UserResponse> userData = _userData;

    private final MutableLiveData<UserResponse> _updatedUserData = new MutableLiveData<>();
    public LiveData<UserResponse> updatedUserData = _updatedUserData;

    private final MutableLiveData<PasswordResponse> _updatePassword = new MutableLiveData<>();
    public LiveData<PasswordResponse> updatePassword = _updatePassword;

    @Inject
    public EditViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }

    public void getUser() {
        repository.getUser().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    _userData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
                _userData.setValue(new UserResponse("", "Please Check Internet connection!", null));
            }
        });
    }

    public void updateUserData(User user) {
        repository.updateUserData(user.getName(), user.getEmail(), Integer.parseInt(user.getAllowToSaveImage())).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    _updatedUserData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
                _userData.setValue(new UserResponse("", "Please Check Internet connection!", null));
            }
        });
    }

    public void updateUserPassword(String oldPassword, String newPassword) {
        repository.updateUserPassword(oldPassword, newPassword).enqueue(new Callback<PasswordResponse>() {
            @Override
            public void onResponse(Call<PasswordResponse> call, Response<PasswordResponse> response) {
                if (response.isSuccessful()) {
                    _updatePassword.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PasswordResponse> call, Throwable t) {
                _updatePassword.setValue(new PasswordResponse("", "Please Check Internet connection!", null));
            }
        });
    }
}
