package com.example.graduationproject.presentation.main.scan;

import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ScanViewModel extends ViewModel {

    private final RepositoryImpl repository;

//    private final MutableLiveData<LoginResponse> loginDataMutableLiveData = new MutableLiveData<>();
//    public LiveData<LoginResponse> loginDataLiveData = loginDataMutableLiveData;


    @Inject
    public ScanViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }

    String getFakeText() {
        return "fake";
    }

//    public void login() {
//        Single<LoginResponse> observable = repository.login()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
//
//        observable.subscribe(value ->
//                loginDataMutableLiveData.setValue(value), error ->
//                Log.e("TAG", "login: " + error)
//        );
//    }


}
