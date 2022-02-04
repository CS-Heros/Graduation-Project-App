package com.example.graduationproject.domian.model.updateUserImage;

import com.example.graduationproject.common.ResponseType;
import com.example.graduationproject.core.BaseResponse;

public class UserImageResponse extends BaseResponse {
    private UserImage data;

    public UserImageResponse(String type, String error, ResponseType status, UserImage data) {
        super(type, error, status);
        this.data = data;
    }

    public UserImage getData() {
        return data;
    }

    public void setData(UserImage data) {
        this.data = data;
    }
}
