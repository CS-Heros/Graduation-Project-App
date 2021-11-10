package com.example.graduationproject.presentation.main.result;

import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ResultViewModel extends ViewModel {

    private final RepositoryImpl repository;

    @Inject
    public ResultViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }


}
