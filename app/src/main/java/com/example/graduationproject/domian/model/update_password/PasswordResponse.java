package com.example.graduationproject.domian.model.update_password;

import com.example.graduationproject.core.BaseResponse;

public class PasswordResponse extends BaseResponse {
    private PasswordMsg data;

    public PasswordResponse(String type, String error, PasswordMsg data) {
        super(type, error);
        this.data = data;
    }

    public PasswordMsg getData() {
        return data;
    }

    public void setData(PasswordMsg data) {
        this.data = data;
    }
}
