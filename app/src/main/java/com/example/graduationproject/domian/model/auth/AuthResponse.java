package com.example.graduationproject.domian.model.auth;

import com.example.graduationproject.core.BaseResponse;

public class AuthResponse extends BaseResponse {
    private Auth data;

    public AuthResponse(String status, String errors, Auth data) {
        super(status, errors);
        this.data = data;
    }

    public Auth getData() {
        return data;
    }

    public void setData(Auth data) {
        this.data = data;
    }
}
