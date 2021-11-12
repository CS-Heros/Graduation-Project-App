package com.example.graduationproject.domian.model.user;

import com.example.graduationproject.core.BaseResponse;

public class UserResponse extends BaseResponse {
    private User data;

    public UserResponse(String type, String error, User data) {
        super(type, error);
        this.data = data;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
