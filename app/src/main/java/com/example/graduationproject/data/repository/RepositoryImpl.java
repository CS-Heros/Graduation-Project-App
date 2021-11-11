package com.example.graduationproject.data.repository;

import com.example.graduationproject.data.data_source.network.ApiService;
import com.example.graduationproject.domian.model.auth.AuthResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class RepositoryImpl {
    private ApiService apiService;

    @Inject
    public RepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<AuthResponse> login(String email, String password) {
        return apiService.login(email, password);
    }

    public Single<AuthResponse> register(String name, String email, String password) {
        return apiService.register(name, email, password);
    }
}
