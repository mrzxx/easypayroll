package com.easypayroll.payrollservice.dto;

import com.easypayroll.payrollservice.model.Gender;

public record WorkerResponse(Long id,String name,String lastname ,Integer age,Gender gender,String identity,Integer degree,PositionResponse position) {

}