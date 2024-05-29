package com.example.ivt22ServerDemo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Unit {
    @JsonProperty("surname")
    private String name ="Лыткин";
    private int x;
    private int y;
}
