package com.example.graduationproject.presentation.main.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduationproject.common.RetrofitUtils;
import com.example.graduationproject.data.repository.RepositoryImpl;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

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
        RetrofitUtils.safeCall(repository.getFakeListResponse(), fakeListResponse -> {
            fakeListResponseDataMutableLiveData.setValue(fakeListResponse);
            return null;
        }, (error, responseType) -> {
            fakeListResponseDataMutableLiveData.setValue(new FakeListResponse("", error, responseType, null));
            return null;
        });


//        repository.getFakeListResponse().enqueue(new Callback<FakeListResponse>() {
//            @Override
//            public void onResponse(Call<FakeListResponse> call, Response<FakeListResponse> response) {
//                if (response.isSuccessful()) {
//                    fakeListResponseDataMutableLiveData.setValue(response.body());
//                } else if (response.code() == 401) {
//                    fakeListResponseDataMutableLiveData.setValue(
//                            new FakeListResponse("", "", ResponseType.AU_AUTHORIZED, null)
//                    );
//                } else {
//                    fakeListResponseDataMutableLiveData.setValue(
//                            new FakeListResponse("", "Something went wrong please try again!", ResponseType.FAIL, null)
//                    );
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FakeListResponse> call, Throwable t) {
//                fakeListResponseDataMutableLiveData.setValue(
//                        new FakeListResponse("", "Please Check Internet connection!", ResponseType.FAIL, null)
//                );
//            }
//        });
    }

}
