package com.easypayroll.companyservice.dto;

public record PositionResponse(
    Long id,
    String title,
    Integer dailyCost,
    Integer extraDailyCost
) {}
