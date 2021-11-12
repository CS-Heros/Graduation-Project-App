package com.example.graduationproject.presentation.main.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final RepositoryImpl repository;

    private final MutableLiveData<FakeListResponse> fakeListResponseDataMutableLiveData = new MutableLiveData<>();
    public LiveData<FakeListResponse> fakeListResponseDataLiveData = fakeListResponseDataMutableLiveData;

    @Inject
    public HomeViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }

    public void getFakeListResponse() {
//        Observable<FakeListResponse> observable = repository.getFakeListResponse()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
//
//        observable.subscribe(fakeListResponseDataMutableLiveData::setValue, error -> {
//            Log.e("TAG", "getFakeListResponse: " + error.getLocalizedMessage());
//        });

        repository.getFakeListResponse().enqueue(new Callback<FakeListResponse>() {
            @Override
            public void onResponse(Call<FakeListResponse> call, Response<FakeListResponse> response) {
                if (response.isSuccessful()) {
                    fakeListResponseDataMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FakeListResponse> call, Throwable t) {

            }
        });
    }

}
