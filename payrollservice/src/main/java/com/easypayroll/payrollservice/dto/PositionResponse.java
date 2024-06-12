package com.easypayroll.payrollservice.dto;

public record PositionResponse(
    Long id,
    String title,
    Integer dailyCost,
    Integer extraDailyCost
) {}
