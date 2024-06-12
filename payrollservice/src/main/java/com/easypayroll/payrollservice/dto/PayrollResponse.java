package com.easypayroll.payrollservice.dto;

import java.util.Date;

public record PayrollResponse(
    Long id,
    String identity,
    Integer grossCost,
    Double netCost,
    Double employerCost,
    Integer workedDay,
    Double sgkPrim,
    Double sgkUnemploymentPrim,
    Double incomeTax,
    Double stampTax,
    Double sgkEmployerPrim,
    Double sgkUnemploymentEmployerPrim,
    Date paymentDate,
    Date creationDate
) {}
