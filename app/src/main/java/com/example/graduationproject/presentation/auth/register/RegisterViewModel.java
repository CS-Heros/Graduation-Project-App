package com.example.graduationproject.presentation.auth.register;

import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RegisterViewModel extends ViewModel {

    private final RepositoryImpl repository;

    @Inject
    public RegisterViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }


    public void register(String name, String password) {

    }
}
