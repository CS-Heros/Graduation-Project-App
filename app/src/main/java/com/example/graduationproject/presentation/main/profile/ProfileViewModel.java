package com.example.graduationproject.presentation.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;
import com.example.graduationproject.domian.model.updateUserImage.UserImageResponse;
import com.example.graduationproject.domian.model.user.UserResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ProfileViewModel extends ViewModel {

    private final RepositoryImpl repository;

    private final MutableLiveData<UserResponse> _userData = new MutableLiveData<>();
    public LiveData<UserResponse> userData = _userData;

    private final MutableLiveData<UserImageResponse> _userImageData = new MutableLiveData<>();
    public LiveData<UserImageResponse> userImageData = _userImageData;


    @Inject
    public ProfileViewModel(RepositoryImpl repository) {
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

    public void updateUserImage(MultipartBody.Part img) {
        repository.updateUserImage(img).enqueue(new Callback<UserImageResponse>() {
            @Override
            public void onResponse(Call<UserImageResponse> call, Response<UserImageResponse> response) {
                if (response.isSuccessful()) {
                    _userImageData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserImageResponse> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
                _userImageData.setValue(new UserImageResponse("", "Please Check Internet connection!", null));
            }
        });
    }
}
