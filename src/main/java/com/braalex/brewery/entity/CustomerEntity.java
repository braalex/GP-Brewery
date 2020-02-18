package com.braalex.brewery.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Table(name = "customer")
@Entity
public class CustomerEntity extends BaseEntity {
    private String category;
    private String companyName;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
