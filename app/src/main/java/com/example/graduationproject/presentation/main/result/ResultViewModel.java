package com.example.graduationproject.presentation.main.result;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduationproject.common.ResponseType;
import com.example.graduationproject.data.repository.RepositoryImpl;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ResultViewModel extends ViewModel {

    private final RepositoryImpl repository;

    private final MutableLiveData<FakeListResponse> _fakeList = new MutableLiveData<>();
    public LiveData<FakeListResponse> fakeList = _fakeList;

    @Inject
    public ResultViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }

    public void getFakeListResponseById(long id) {
        repository.getFakeListResponseById(id).enqueue(new Callback<FakeListResponse>() {
            @Override
            public void onResponse(Call<FakeListResponse> call, Response<FakeListResponse> response) {
                if (response.isSuccessful()) {
                    _fakeList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FakeListResponse> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
                _fakeList.
                        setValue(new FakeListResponse("", "Please Check Internet connection!", ResponseType.FAIL,null));
            }
        });
    }

}
