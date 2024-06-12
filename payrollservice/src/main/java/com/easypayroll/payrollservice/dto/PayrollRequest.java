package com.easypayroll.payrollservice.dto;

import java.util.Date;

public record PayrollRequest(
    String identity,
    Integer workedDay,
    Date paymentDate
) {}