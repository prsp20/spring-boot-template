package com.prakass.example.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExampleDTO {
    private final String name;
    private final String content;

    @JsonCreator
    public ExampleDTO(@JsonProperty("name") String name,
                      @JsonProperty("content") String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
