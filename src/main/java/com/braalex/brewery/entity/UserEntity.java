package com.braalex.brewery.entity;

import com.braalex.brewery.security.UserRole;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Table(name = "user")
@Entity
public class UserEntity extends BaseEntity {
    private String email;
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;
}
