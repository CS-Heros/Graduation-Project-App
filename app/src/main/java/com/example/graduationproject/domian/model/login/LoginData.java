package com.example.graduationproject.domian.model.login;

import com.example.graduationproject.core.BaseResponse;

import java.util.List;

public class LoginData extends BaseResponse {
    private Login data;

    public LoginData(String type, List<String> errors, Login data) {
        super(type, errors);
        this.data = data;
    }

    public Login getData() {
        return data;
    }

    public void setData(Login data) {
        this.data = data;
    }
}