package com.easypayroll.payrollservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.easypayroll.payrollservice.dto.WorkerResponse;

@Component
public interface WorkerClient {
    @GetExchange("/api/worker/identity/{identity}")
    WorkerResponse getWorkerByIdentity(@PathVariable("identity") String identity);
}
