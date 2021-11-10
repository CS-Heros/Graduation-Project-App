package com.example.graduationproject.presentation;

import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EmptyViewModel extends ViewModel {

    private final RepositoryImpl repository;

    @Inject
    public EmptyViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }


}
