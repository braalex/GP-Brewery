package com.braalex.brewery.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Table(name = "staff")
@Entity
public class StaffEntity extends UserEntity {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
