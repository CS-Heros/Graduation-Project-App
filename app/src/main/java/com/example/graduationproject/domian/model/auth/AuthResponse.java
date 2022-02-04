package com.example.graduationproject.domian.model.auth;

import com.example.graduationproject.common.ResponseType;
import com.example.graduationproject.core.BaseResponse;

public class AuthResponse extends BaseResponse {
    private Auth data;

    public AuthResponse(String type, String error, ResponseType status, Auth data) {
        super(type, error, status);
        this.data = data;
    }

    public Auth getData() {
        return data;
    }

    public void setData(Auth data) {
        this.data = data;
    }
}
