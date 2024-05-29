package com.example.ivt22ServerDemo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;
import java.util.TreeMap;

public class User {

    private Map<String, Object> params = new TreeMap<>();

    @JsonAnyGetter
    public Map<String, Object> getParams() {
        return params;
    }
}
