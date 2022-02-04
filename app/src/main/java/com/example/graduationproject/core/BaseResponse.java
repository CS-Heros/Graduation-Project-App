package com.example.graduationproject.core;

import com.example.graduationproject.common.ResponseType;

public class BaseResponse {
    private String type;
    private String error;
    private ResponseType status;

    public BaseResponse(String type, String error, ResponseType status) {
        this.type = type;
        this.error = error;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ResponseType getStatus() {
        if (status==null) status = ResponseType.SUCCESS;
        return status;
    }

    public void setStatus(ResponseType status) {
        this.status = status;
    }
}