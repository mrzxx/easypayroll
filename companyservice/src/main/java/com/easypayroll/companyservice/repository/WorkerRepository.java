package com.easypayroll.companyservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easypayroll.companyservice.model.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Long> {
    boolean existsByPositionId(Long positionId);
    Optional<Worker> findByIdentity(String identity);
}
