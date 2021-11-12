package com.example.graduationproject.presentation.main.result;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduationproject.data.repository.RepositoryImpl;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ResultViewModel extends ViewModel {

    private final RepositoryImpl repository;

    private final MutableLiveData<FakeListResponse> _diseaseImage = new MutableLiveData<>();
    public LiveData<FakeListResponse> diseaseImage = _diseaseImage;

    @Inject
    public ResultViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }

    public void uploadPhoto(MultipartBody.Part img) {
        repository.uploadPhoto(img).enqueue(new Callback<FakeListResponse>() {
            @Override
            public void onResponse(Call<FakeListResponse> call, Response<FakeListResponse> response) {
                if (response.isSuccessful()) {
                    _diseaseImage.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FakeListResponse> call, Throwable t) {
                _diseaseImage.setValue(new FakeListResponse("", "Please Check Internet connection!", null));
            }
        });
    }
}
