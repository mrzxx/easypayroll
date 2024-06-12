package com.easypayroll.companyservice.dto;

import com.easypayroll.companyservice.model.Gender;

public record WorkerRequest(Long id,String name,String lastname ,Integer age,Gender gender,String identity,Integer degree,PositionRequest position) {

}
