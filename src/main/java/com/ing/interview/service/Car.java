package com.ing.interview.service;

import static javax.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
public class Car {

    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "Sequence", strategy = SEQUENCE)
    @SequenceGenerator(name = "Sequence", sequenceName = "SEQUENCE_CAR", allocationSize = 1)
    @Id
    private Long id;

    private String color;

    private String model;

    private LocalDate orderDate;

}
