package com.braalex.brewery.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "brew")
@Entity
public class BrewEntity extends BaseEntity {
    private Long brewerId;
    private Long beerId;
    private LocalDate startDate;
    private LocalDate endDate;
}
