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

import com.easypayroll.companyservice.dto.PositionResponse;
import com.easypayroll.companyservice.dto.PositionUpdateRequest;
import com.easypayroll.companyservice.dto.WorkerResponse;
import com.easypayroll.companyservice.model.Position;
import com.easypayroll.companyservice.service.PositionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/position")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;

    @Operation(summary = "Yeni Pozisyon Ekleme Metodu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Çalışan başarıyla eklendi",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkerResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Geçersiz istek",
                    content = @Content)})

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PositionResponse createPosition(@RequestBody PositionResponse positionResponse){
        return positionService.addPosition(positionResponse);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PositionResponse> listPosition(){
        return positionService.listPositions();
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Position deletePositionById(@PathVariable Long id){
        return positionService.deletePosition(id);
    }
    
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PositionResponse updateWorker(PositionUpdateRequest positionRequest){

        return positionService.updatePosition(positionRequest);

    }

}
