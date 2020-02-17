package com.braalex.brewery.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "auth_info")
public class AuthInfoEntity extends BaseEntity {
    private String login;
    private String password;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
