package com.example.graduationproject.common;

public class TestCode {

    //        repository.getFakeListResponse().enqueue(new Callback<FakeListResponse>() {
    //            @Override
    //            public void onResponse(Call<FakeListResponse> call, Response<FakeListResponse> response) {
    //                if (response.isSuccessful()) {
    //                    fakeListResponseDataMutableLiveData.setValue(response.body());
    //                } else {
    //                    fakeListResponseDataMutableLiveData.
    //                            setValue(new FakeListResponse("", "", ResponseType.AU_AUTHORIZED, null));
    //
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<FakeListResponse> call, Throwable t) {
    //                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
    //                fakeListResponseDataMutableLiveData.
    //                        setValue(new FakeListResponse("", "Please Check Internet connection!", ResponseType.FAIL, null));
    //            }
    //        });
    //        method(repository.getFakeListResponse(), this);
    //
    //
    //    }
    //
    //    public <T extends BaseResponse> void method(Call<T> b, TestI listener) {
    //
    //        b.enqueue(new Callback<T>() {
    //            @Override
    //            public void onResponse(Call<T> call, Response<T> response) {
    //                if (response.isSuccessful()) {
    //                    listener.onSuccess(response.body());
    //                } else {
    //                    listener.onFail(new BaseResponse("", "", ResponseType.AU_AUTHORIZED));
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<T> call, Throwable t) {
    //                listener.onFail(new BaseResponse("", "", ResponseType.FAIL));
    //            }
    //        });
    //    }
    //
    //    @Override
    //    public <T extends BaseResponse> T onSuccess(T body) {
    //        fakeListResponseDataMutableLiveData.setValue((FakeListResponse) body);
    //        return null;
    //    }
    //
    //    @Override
    //    public <T extends BaseResponse> T onFail(T body) {
    //        return null;
    //    }

    //    public interface TestI {
    //        <T extends BaseResponse> T onSuccess(T body);
    //        <T extends BaseResponse> T onFail(T body);
    //    }



    //        Observable<FakeListResponse> observable = repository.getFakeListResponse()
    //                .subscribeOn(Schedulers.io())
    //                .observeOn(AndroidSchedulers.mainThread());
    //
    //
    //        observable.subscribe(fakeListResponseDataMutableLiveData::setValue, error -> {
    //            Log.e("TAG", "getFakeListResponse: " + error.getLocalizedMessage());
    //        });

}
