package com.example.graduationproject.domian.model.fakeListResponse;

import java.util.List;

public class FakeListData {
    private List<FakeListItem> diseases;
    private List<FakeListItem> precautions;
    private List<FakeListItem> symptoms;

    public FakeListData(List<FakeListItem> diseases, List<FakeListItem> precautions, List<FakeListItem> symptoms) {
        this.diseases = diseases;
        this.precautions = precautions;
        this.symptoms = symptoms;
    }

    public List<FakeListItem> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<FakeListItem> diseases) {
        this.diseases = diseases;
    }

    public List<FakeListItem> getPrecautions() {
        return precautions;
    }

    public void setPrecautions(List<FakeListItem> precautions) {
        this.precautions = precautions;
    }

    public List<FakeListItem> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<FakeListItem> symptoms) {
        this.symptoms = symptoms;
    }
}
