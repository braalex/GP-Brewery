package com.braalex.brewery.repository;

import com.braalex.brewery.entity.BeerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<BeerEntity, Long> {

}
