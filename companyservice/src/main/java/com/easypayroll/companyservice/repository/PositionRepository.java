package com.easypayroll.companyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easypayroll.companyservice.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {

}
