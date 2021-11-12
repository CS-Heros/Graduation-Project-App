package com.example.graduationproject.data.repository;

import com.example.graduationproject.common.SharedPreferenceManger;
import com.example.graduationproject.data.data_source.network.ApiService;
import com.example.graduationproject.domian.model.auth.AuthResponse;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListResponse;
import com.example.graduationproject.domian.model.user.UserResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;
import retrofit2.Call;

public class RepositoryImpl {
    private ApiService apiService;
    private SharedPreferenceManger sharedPreferenceManger;


    @Inject
    public RepositoryImpl(ApiService apiService, SharedPreferenceManger sharedPreferenceManger) {
        this.apiService = apiService;
        this.sharedPreferenceManger = sharedPreferenceManger;
    }

    public Single<AuthResponse> login(String email, String password) {
        return apiService.login(email, password);
    }

    public Single<AuthResponse> register(String name, String email, String password) {
        return apiService.register(name, email, password);
    }

    public Call<FakeListResponse> getFakeListResponse() {
        return apiService.getFakeListResponse(sharedPreferenceManger.getToken());
    }

    public Call<UserResponse> getUser() {
        return apiService.getUser(sharedPreferenceManger.getToken());
    }

    public Call<FakeListResponse> uploadPhoto(MultipartBody.Part img) {
        return apiService.uploadPhoto(sharedPreferenceManger.getToken(), img);
    }
}
