package com.braalex.brewery.repository;

import com.braalex.brewery.entity.BeerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<BeerEntity, Long> {

}
