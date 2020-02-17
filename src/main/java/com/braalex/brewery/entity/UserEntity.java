package com.braalex.brewery.entity;

import com.braalex.brewery.security.UserRole;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public abstract class UserEntity extends BaseEntity {
    private String email;
    private String password;
    private UserRole userRole;
}
