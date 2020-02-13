package com.braalex.brewery.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "staff")
@Entity
public class StaffEntity extends UserEntity {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
