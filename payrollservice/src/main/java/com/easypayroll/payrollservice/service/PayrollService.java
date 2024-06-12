package com.easypayroll.payrollservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.easypayroll.payrollservice.client.WorkerClient;
import com.easypayroll.payrollservice.dto.PayrollRequest;
import com.easypayroll.payrollservice.dto.PayrollResponse;
import com.easypayroll.payrollservice.dto.WorkerResponse;
import com.easypayroll.payrollservice.exception.ResourceNotFoundException;
import com.easypayroll.payrollservice.model.Payroll;
import com.easypayroll.payrollservice.repository.PayrollRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PayrollService {
    private final PayrollRepository payrollRepository;
    private final WorkerClient workerClient;

    private final Integer sgkPrimRatio=14;
    private final Integer sgkUnemploymentPrimRatio=1;
    private final Double stamptTaxRatio=0.759;
    private final Integer sgkEmployerPrimRatio=20;
    private final Integer sgkUnemploymentEmployerPrimRatio=2;

    public PayrollResponse createPayroll(PayrollRequest payrollRequest) {
        WorkerResponse worker = workerClient.getWorkerByIdentity(payrollRequest.identity());

        Integer dailyCost = worker.position().dailyCost();
        Integer grossCost = dailyCost*payrollRequest.workedDay();

        Double sgkPrim = (double) (grossCost*sgkPrimRatio/100);
        Double sgkUnemploymentPrim = (double) (grossCost*sgkUnemploymentPrimRatio/100);
        Double income = grossCost - (sgkPrim+sgkUnemploymentPrim);
        Double incomeTax = income*15/100;//Gelire göre değişiyor şimdilik sabit oran.
        Double stamptTax = grossCost*stamptTaxRatio/100;
        Double sgkEmployerPrim = (double) (grossCost*sgkEmployerPrimRatio/100);
        Double sgkUnemploymentEmployerPrim = (double) (grossCost*sgkUnemploymentEmployerPrimRatio/100);

        Double netCost = grossCost - (sgkPrim+sgkUnemploymentPrim+incomeTax+stamptTax);
        Double employerCost = grossCost+sgkEmployerPrim+sgkUnemploymentEmployerPrim;

        Payroll payroll = Payroll.builder()
                .identity(worker.identity())
                .grossCost(grossCost)
                .netCost(netCost)
                .employerCost(employerCost)
                .workedDay(payrollRequest.workedDay())
                .sgkPrim(sgkPrim)
                .sgkUnemploymentPrim(sgkUnemploymentPrim)
                .incomeTax(incomeTax)
                .stampTax(stamptTax)
                .sgkEmployerPrim(sgkEmployerPrim)
                .sgkUnemploymentEmployerPrim(sgkUnemploymentEmployerPrim)
                .paymentDate(payrollRequest.paymentDate())
                .build();

        Payroll savedPayroll = payrollRepository.save(payroll);
        return toPayrollResponse(savedPayroll);
    }

    public List<PayrollResponse> listPayrolls() {
        List<Payroll> payrolls = payrollRepository.findAll();
        return payrolls.stream()
                       .map(this::toPayrollResponse)
                       .collect(Collectors.toList());
    }

    public PayrollResponse deletePayroll(Long id) {
        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bordro bulunamadı: " + id));
        payrollRepository.deleteById(id);
        return toPayrollResponse(payroll);
    }

    private PayrollResponse toPayrollResponse(Payroll payroll) {
        return new PayrollResponse(
                payroll.getId(),
                payroll.getIdentity(),
                payroll.getGrossCost(),
                payroll.getNetCost(),
                payroll.getEmployerCost(),
                payroll.getWorkedDay(),
                payroll.getSgkPrim(),
                payroll.getSgkUnemploymentPrim(),
                payroll.getIncomeTax(),
                payroll.getStampTax(),
                payroll.getSgkEmployerPrim(),
                payroll.getSgkUnemploymentEmployerPrim(),
                payroll.getPaymentDate(),
                payroll.getCreationDate()
        );
    }
}
