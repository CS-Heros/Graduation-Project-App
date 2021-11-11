package com.example.graduationproject.presentation.auth.register;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;
import com.example.graduationproject.domian.model.auth.AuthResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class RegisterViewModel extends ViewModel {

    private final RepositoryImpl repository;

    private final MutableLiveData<AuthResponse> authDataMutableLiveData = new MutableLiveData<>();
    public LiveData<AuthResponse> authDataLiveData = authDataMutableLiveData;

    @Inject
    public RegisterViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }


    public void register(String name, String email, String password) {
        Single<AuthResponse> observable = repository.register(name, email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(authDataMutableLiveData::setValue, error ->
                Log.e("TAG", "login: " + error)
        );
    }
}
