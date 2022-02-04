package com.example.graduationproject.domian.model.user;

import com.example.graduationproject.common.ResponseType;
import com.example.graduationproject.core.BaseResponse;

public class UserResponse extends BaseResponse {
    private User data;

    public UserResponse(String type, String error, ResponseType status, User data) {
        super(type, error, status);
        this.data = data;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
