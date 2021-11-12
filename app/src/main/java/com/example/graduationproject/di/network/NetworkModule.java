package com.example.graduationproject.di.network;


import com.example.graduationproject.BuildConfig;
import com.example.graduationproject.common.Constants;
import com.example.graduationproject.common.SharedPreferenceManger;
import com.example.graduationproject.data.data_source.network.ApiService;
import com.example.graduationproject.data.repository.RepositoryImpl;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {


    @Provides
    @Singleton
    public static ApiService providePokemonApiService(
            OkHttpClient okHttpClient
    ) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiService.class);
    }

    @Provides
    @Singleton
    public static RepositoryImpl provideCovidRepository(ApiService apiService, SharedPreferenceManger sharedPreferenceManger) {
        return new RepositoryImpl(apiService, sharedPreferenceManger);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor providesLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder client = new OkHttpClient().newBuilder()
                .callTimeout(35, TimeUnit.SECONDS)
                .connectTimeout(35, TimeUnit.SECONDS)
                .readTimeout(35, TimeUnit.SECONDS)
                .writeTimeout(35, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) client.addInterceptor(loggingInterceptor);
        return client.build();
    }

}
