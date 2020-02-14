package com.braalex.brewery.repository;

import com.braalex.brewery.entity.BrewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrewRepository extends JpaRepository<BrewEntity, Long> {

    List<BrewEntity> findAllByBrewerId(Long id);
}
