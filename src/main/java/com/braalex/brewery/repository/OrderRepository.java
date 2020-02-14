package com.braalex.brewery.repository;

import com.braalex.brewery.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByCustomerId(Long id);
}
