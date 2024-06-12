package com.easypayroll.payrollservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easypayroll.payrollservice.model.Payroll;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll,Long> {

}
