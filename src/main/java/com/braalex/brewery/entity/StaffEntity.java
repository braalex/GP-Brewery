package com.braalex.brewery.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Table(name = "staff")
@Entity
public class StaffEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
