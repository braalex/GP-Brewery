package com.braalex.brewery.repository;

import com.braalex.brewery.entity.BrewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrewRepository extends JpaRepository<BrewEntity, Long> {

    List<BrewEntity> findAllByBrewerId(Long id);
}
