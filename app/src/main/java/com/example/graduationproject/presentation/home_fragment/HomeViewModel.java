package com.example.graduationproject.presentation.home_fragment;

import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private RepositoryImpl repository;


    @Inject
    public HomeViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }

    String getFakeText(){
        return "fake";
    }

}
