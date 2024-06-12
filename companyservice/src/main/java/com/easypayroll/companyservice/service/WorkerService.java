package com.easypayroll.companyservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.easypayroll.companyservice.dto.PositionResponse;
import com.easypayroll.companyservice.dto.WorkerRequest;
import com.easypayroll.companyservice.dto.WorkerResponse;
import com.easypayroll.companyservice.exception.ResourceNotFoundException;
import com.easypayroll.companyservice.model.Position;
import com.easypayroll.companyservice.model.Worker;
import com.easypayroll.companyservice.repository.PositionRepository;
import com.easypayroll.companyservice.repository.WorkerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final PositionRepository positionRepository;
    
    public WorkerResponse createWorker(WorkerRequest workerRequest){

        Position position = positionRepository.findById(workerRequest.position().id())
        .orElseThrow(() -> new ResourceNotFoundException("Invalid position ID: " + workerRequest.position().id()));

        Worker worker = Worker.builder()
        .name(workerRequest.name())
        .age(workerRequest.age())
        .degree(workerRequest.degree())
        .gender(workerRequest.gender())
        .lastname(workerRequest.lastname())
        .identity(workerRequest.identity())
        .position(position)
        .build();

        Worker savedWorker = workerRepository.save(worker);
        return toWorkerResponse(savedWorker);
    }

    private WorkerResponse toWorkerResponse(Worker worker) {
        PositionResponse positionResponse = new PositionResponse(
            worker.getPosition().getId(),
            worker.getPosition().getTitle(),
            worker.getPosition().getDailyCost(),
            worker.getPosition().getExtraDailyCost()
        );

        return new WorkerResponse(
            worker.getId(),
            worker.getName(),
            worker.getLastname(),
            worker.getAge(),
            worker.getGender(),
            worker.getIdentity(),
            worker.getDegree(),
            positionResponse
        );
    }

    public List<WorkerResponse> listWorkers() {
        List<Worker> workers = workerRepository.findAll();
        return workers.stream()
                      .map(this::toWorkerResponse)
                      .collect(Collectors.toList());
    }

    public WorkerResponse deleteWorker(Long id){
        Worker worker = workerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("İlgili id'de çalışan bulunamadı"));
        workerRepository.deleteById(id);
        return toWorkerResponse(worker);

    }

    public WorkerResponse updateWorker(WorkerRequest workerRequest) {
        Worker existingWorker = workerRepository.findById(workerRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Çalışan bulunamadı: " + workerRequest.id()));

        Position position = positionRepository.findById(workerRequest.position().id())
                .orElseThrow(() -> new ResourceNotFoundException("Geçersiz pozisyon: " + workerRequest.position().id()));

        existingWorker.setName(workerRequest.name());
        existingWorker.setAge(workerRequest.age());
        existingWorker.setDegree(workerRequest.degree());
        existingWorker.setGender(workerRequest.gender());
        existingWorker.setLastname(workerRequest.lastname());
        existingWorker.setIdentity(workerRequest.identity());
        existingWorker.setPosition(position);

        Worker updatedWorker = workerRepository.save(existingWorker);

        return toWorkerResponse(updatedWorker);
    }

    public WorkerResponse getWorkerByIdentity(String identity) {
        Worker worker = workerRepository.findByIdentity(identity)
                .orElseThrow(() -> new ResourceNotFoundException("İlgili TcKimliğe sahip çalışan bulunamadı: " + identity));
        return toWorkerResponse(worker);
    }

}
