package com.manorama.SpringProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manorama.SpringProject.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findAllByuserId(long id);
	}
