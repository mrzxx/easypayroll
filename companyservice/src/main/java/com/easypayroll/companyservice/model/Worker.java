package com.easypayroll.companyservice.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "workers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Worker {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer version;

    private String name;

    private String lastname;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;   

    @Column(name="identity",unique = true)
    private String identity;

    private Integer degree;

    @ManyToOne
    @JoinColumn(name = "position_id",nullable = false)
    private Position position;

    public void orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }
}
