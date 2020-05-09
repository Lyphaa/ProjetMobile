package com.example.application_1;

import android.graphics.Region;

import java.util.List;

public class RegionPkmnResponse {

    private Integer count;
    private String next;
    private List<RegionPkmn> result;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<RegionPkmn> getResult() {
        return result;
    }

}
