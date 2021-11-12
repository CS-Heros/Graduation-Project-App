package com.example.graduationproject.domian.model.user;

public class UserResponse {
    private User data;

    public UserResponse(User data) {
        this.data = data;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
