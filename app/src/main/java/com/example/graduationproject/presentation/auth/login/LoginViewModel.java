package com.example.graduationproject.presentation.auth.login;

import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final RepositoryImpl repository;

    @Inject
    public LoginViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }


    public void login(String email, String password) {

    }
}
