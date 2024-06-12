package com.easypayroll.companyservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record PositionRequest(Long id) {
    @JsonCreator
    public PositionRequest(@JsonProperty("id") Long id){
        this.id = id;
    }
}
