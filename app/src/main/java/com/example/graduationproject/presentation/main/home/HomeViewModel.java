package com.example.graduationproject.presentation.main.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;
import com.example.graduationproject.domian.model.login.LoginResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final RepositoryImpl repository;

    private final MutableLiveData<LoginResponse> loginDataMutableLiveData = new MutableLiveData<>();
    public LiveData<LoginResponse> loginDataLiveData = loginDataMutableLiveData;


    @Inject
    public HomeViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }

    String getFakeText() {
        return "fake";
    }

    public void login() {
        Single<LoginResponse> observable = repository.login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        observable.subscribe(value ->
                loginDataMutableLiveData.setValue(value), error ->
                Log.e("TAG", "login: " + error)
        );
    }


}
