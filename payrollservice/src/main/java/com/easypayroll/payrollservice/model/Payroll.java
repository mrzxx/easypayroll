package com.easypayroll.payrollservice.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "payrolls")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String identity;

    private Integer grossCost;

    private Integer workedDay;

    private Double sgkPrim;

    private Double sgkUnemploymentPrim;

    private Double incomeTax;

    private Double stampTax;

    private Double sgkEmployerPrim;

    private Double sgkUnemploymentEmployerPrim;

    private Double netCost;

    private Double employerCost;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date paymentDate;

    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }
    

}
