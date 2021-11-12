package com.example.graduationproject.data.data_source.network;

import com.example.graduationproject.domian.model.auth.AuthResponse;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListResponse;
import com.example.graduationproject.domian.model.user.UserResponse;

import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    @POST("login")
    Call<AuthResponse> login(@Query("email") String email, @Query("password") String password);

    @POST("register")
    Call<AuthResponse> register(@Query("name") String name, @Query("email") String email, @Query("password") String password);

    @GET("diseases")
    Call<FakeListResponse> getFakeListResponse(@Header("Authorization") String token);

    @GET("get_user_data")
    Call<UserResponse> getUser(@Header("Authorization") String token);

    @Multipart
    @POST("diseases")
    Call<FakeListResponse> uploadPhoto(@Header("Authorization") String token, @Part MultipartBody.Part img);

}