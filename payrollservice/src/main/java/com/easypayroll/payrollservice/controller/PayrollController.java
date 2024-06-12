package com.easypayroll.payrollservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.easypayroll.payrollservice.dto.PayrollRequest;
import com.easypayroll.payrollservice.dto.PayrollResponse;
import com.easypayroll.payrollservice.service.PayrollService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
public class PayrollController {
    private final PayrollService payrollService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PayrollResponse createPayroll(@RequestBody PayrollRequest payrollRequest) {
        return payrollService.createPayroll(payrollRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PayrollResponse> listPayrolls() {
        return payrollService.listPayrolls();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PayrollResponse deletePayroll(@PathVariable Long id) {
        return payrollService.deletePayroll(id);
    }
}
