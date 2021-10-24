package com.example.graduationproject.data.repository;

import com.example.graduationproject.data.data_source.network.ApiService;

import javax.inject.Inject;

public class RepositoryImpl {
    private ApiService apiService;

    @Inject
    public RepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }
}
