package com.example.graduationproject.data.data_source.network;

import com.example.graduationproject.domian.model.login.LoginData;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login")
    Single<LoginData> login();

}