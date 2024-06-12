package com.easypayroll.companyservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import com.easypayroll.companyservice.dto.PositionResponse;
import com.easypayroll.companyservice.dto.PositionUpdateRequest;
import com.easypayroll.companyservice.exception.DeleteResrictedException;
import com.easypayroll.companyservice.exception.ResourceNotFoundException;
import com.easypayroll.companyservice.model.Position;
import com.easypayroll.companyservice.repository.PositionRepository;
import com.easypayroll.companyservice.repository.WorkerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;
    private final WorkerRepository workerRepository;
    public PositionResponse addPosition(PositionResponse positionRequest){
        Position position = Position.builder()
        .title(positionRequest.title())
        .dailyCost(positionRequest.dailyCost())
        .extraDailyCost(positionRequest.extraDailyCost())
        .build();
        Position savedPosition = positionRepository.save(position);
        return new PositionResponse(savedPosition.getId(), savedPosition.getTitle(), savedPosition.getDailyCost(), savedPosition.getExtraDailyCost());
    }
    public List<PositionResponse> listPositions() {
        List<Position> positions = positionRepository.findAll();
        return positions.stream()
                        .map(this::toPositionResponse)
                        .collect(Collectors.toList());
    }

    @Transactional
    public Position deletePosition(Long id){
        Position position = positionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("İlgili pozisyon bulunamadı."));

        if (workerRepository.existsByPositionId(id)) {
            throw new DeleteResrictedException("Bu pozisyon silinemez: " + " ilişkili çalışanlar bulundu.");
        }
        
        positionRepository.delete(position);
        return position;
        
    }


    private PositionResponse toPositionResponse(Position position) {
        return new PositionResponse(
                position.getId(),
                position.getTitle(),
                position.getDailyCost(),
                position.getExtraDailyCost()
        );
    }


    public PositionResponse updatePosition(PositionUpdateRequest positionRequest) {
        Position existingPosition = positionRepository.findById(positionRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Geçersiz pozisyon: " + positionRequest.id()));

        existingPosition.setDailyCost(positionRequest.dailyCost());
        existingPosition.setExtraDailyCost(positionRequest.extraDailyCost());
        existingPosition.setTitle(positionRequest.title());

        Position updatedPosition = positionRepository.save(existingPosition);

        return toPositionResponse(updatedPosition);
    }


}
