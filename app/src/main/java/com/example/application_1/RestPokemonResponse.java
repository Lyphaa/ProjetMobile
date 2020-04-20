package com.example.application_1;

import java.util.List;

public class RestPokemonResponse {

    private Integer count;
    private String next;
    private List<Pokemon> result;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Pokemon> getResult() {
        return result;
    }
}
