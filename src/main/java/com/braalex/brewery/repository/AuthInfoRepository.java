package com.braalex.brewery.repository;

import com.braalex.brewery.entity.AuthInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthInfoRepository extends JpaRepository<AuthInfoEntity, Long> {

    Optional<AuthInfoEntity> findByLogin(String email);
}
