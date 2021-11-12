package com.example.graduationproject.domian.model.fakeListResponse;

import com.example.graduationproject.core.BaseResponse;

import java.io.Serializable;

public class FakeListResponse extends BaseResponse {
    private FakeListData data;

    public FakeListResponse(String type, String error, FakeListData data) {
        super(type, error);
        this.data = data;
    }

    public FakeListData getData() {
        return data;
    }

    public void setData(FakeListData data) {
        this.data = data;
    }
}
