package com.easypayroll.companyservice.dto;

public record PositionUpdateRequest(
    Long id,
    String title,
    Integer dailyCost,
    Integer extraDailyCost
) {};