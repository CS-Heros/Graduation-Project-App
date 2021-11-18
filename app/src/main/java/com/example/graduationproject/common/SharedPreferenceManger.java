package com.example.graduationproject.common;

import static com.example.graduationproject.common.Constants.EMPTY_STRING;
import static com.example.graduationproject.common.Constants.HAS_LOGGED_IN;
import static com.example.graduationproject.common.Constants.SHARED_PREFERENCE_FILE_NAME;
import static com.example.graduationproject.common.Constants.TOKEN;
import static com.example.graduationproject.common.Constants.USER_ID;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class SharedPreferenceManger {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;


    @Inject
    public SharedPreferenceManger(@ApplicationContext Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public boolean isHasLoggedIn() {
        return getBooleanValue(HAS_LOGGED_IN);
    }

    public void setHasLoggedIn(boolean hasLoggedIn) {
        setValue(HAS_LOGGED_IN, hasLoggedIn);
    }

    public String getToken() {
        return getStringValue(TOKEN);
    }

    public void setToken(String token) {
        setValue(TOKEN, "Bearer " + token);
    }

    public Long getUserId() {
        return getLongValue(USER_ID);
    }

    public void setUserId(Long userId) {
        setValue(USER_ID, userId);
    }

    private void setValue(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    private void setValue(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    private void setValue(String key, long value) {
        editor.putLong(key, value);
        editor.apply();
    }

    private String getStringValue(String key) {
        return sharedPreferences.getString(key, EMPTY_STRING);
    }

    private boolean getBooleanValue(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    private Long getLongValue(String key) {
        return sharedPreferences.getLong(key, -1);
    }
}
