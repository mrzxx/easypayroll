package com.easypayroll.companyservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.easypayroll.companyservice.dto.WorkerRequest;
import com.easypayroll.companyservice.dto.WorkerResponse;
import com.easypayroll.companyservice.service.WorkerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/worker")
@RequiredArgsConstructor
public class WorkerController {
    
    private final WorkerService workerService;


    @Operation(summary = "Yeni Çalışan Ekleme Metodu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Çalışan başarıyla eklendi",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkerResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Geçersiz istek",
                    content = @Content)})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkerResponse createWorker(@RequestBody WorkerRequest workerRequest){
        return workerService.createWorker(workerRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WorkerResponse> listWorker(){
        return workerService.listWorkers();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public WorkerResponse updateWorker(WorkerRequest workerRequest){

        return workerService.updateWorker(workerRequest);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkerResponse deleteWorker(@PathVariable Long id) {
        return workerService.deleteWorker(id);
    }

    @GetMapping("/identity/{identity}")
    @ResponseStatus(HttpStatus.OK)
    public WorkerResponse getWorkerByIdentity(@PathVariable String identity) {
        return workerService.getWorkerByIdentity(identity);
    }
}
