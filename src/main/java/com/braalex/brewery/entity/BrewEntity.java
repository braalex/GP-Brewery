package com.braalex.brewery.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "brew")
@Entity
public class BrewEntity extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "staff_id")
    private StaffEntity brewer;
    @ManyToOne(optional = false)
    @JoinColumn(name = "beer_id")
    private BeerEntity beer;
    private LocalDate startDate;
    private LocalDate endDate;
}
