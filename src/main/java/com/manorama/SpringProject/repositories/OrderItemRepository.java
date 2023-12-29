package com.manorama.SpringProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manorama.SpringProject.entities.OrderItems;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {}
