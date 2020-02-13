package com.braalex.brewery.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "customer")
@Entity
public class CustomerEntity extends UserEntity {
    private String category;
    private String companyName;
}
