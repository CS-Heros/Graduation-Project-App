package com.example.graduationproject.data.data_source.network;

import com.example.graduationproject.domian.model.auth.AuthResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("login")
    Single<AuthResponse> login(@Query("email") String email, @Query("password") String password);

    @POST("register")
    Single<AuthResponse> register(@Query("name") String name, @Query("email") String email, @Query("password") String password);

}