package com.example.graduationproject.presentation.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.graduationproject.common.SharedPreferenceManger;
import com.example.graduationproject.presentation.auth.AuthActivity;
import com.example.graduationproject.presentation.main.MainActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {

    @Inject
    public SharedPreferenceManger sharedPreferenceManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (sharedPreferenceManger.isHasLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));

        } else {
            startActivity(new Intent(this, AuthActivity.class));
        }
    }
}