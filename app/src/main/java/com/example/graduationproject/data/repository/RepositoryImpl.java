package com.example.graduationproject.data.repository;

import com.example.graduationproject.data.data_source.network.ApiService;
import com.example.graduationproject.domian.model.login.LoginData;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;

public class RepositoryImpl {
    private ApiService apiService;

    @Inject
    public RepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<LoginData> login() {
        return apiService.login();
    }
}
