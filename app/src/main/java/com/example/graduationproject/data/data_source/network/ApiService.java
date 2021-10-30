package com.example.graduationproject.data.data_source.network;

import com.example.graduationproject.domian.model.login.LoginResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login")
    Single<LoginResponse> login();

}