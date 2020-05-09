package com.example.application_1;

import java.util.List;

public class RestApiPersoResponse {
    private Integer count;
    private String next;
    private List<ApiPerso> result;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<ApiPerso> getResult() {
        return result;
    }
}


