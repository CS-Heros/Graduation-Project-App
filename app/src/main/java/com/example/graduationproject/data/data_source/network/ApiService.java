package com.example.graduationproject.data.data_source.network;

import com.example.graduationproject.domian.model.auth.AuthResponse;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListResponse;
import com.example.graduationproject.domian.model.updateUserImage.UserImageResponse;
import com.example.graduationproject.domian.model.update_password.PasswordResponse;
import com.example.graduationproject.domian.model.user.UserResponse;

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

    @GET("diseases")
    Call<FakeListResponse> getFakeListResponseById(@Header("Authorization") String token, @Query("id") long id);

    @GET("get_user_data")
    Call<UserResponse> getUser(@Header("Authorization") String token);

    @Multipart
    @POST("diseases")
    Call<FakeListResponse> uploadPhoto(@Header("Authorization") String token, @Part MultipartBody.Part img);

    @Multipart
    @POST("update_user_avatar")
    Call<UserImageResponse> updateUserImage(@Header("Authorization") String token, @Query("_method") String method, @Part MultipartBody.Part img);

    @POST("update_user_data")
    Call<UserResponse> updateUserData(
            @Header("Authorization") String token,
            @Query("_method") String method,
            @Query("id") Long id,
            @Query("name") String name,
            @Query("email") String email,
            @Query("allowed_to_save_images") int allowToSaveImages
    );

    @POST("update_user_password")
    Call<PasswordResponse> updateUserPassword(
            @Header("Authorization") String token,
            @Query("_method") String method,
            @Query("old_password") String oldPassword,
            @Query("new_password") String newPassword
    );
}