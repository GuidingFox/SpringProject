package com.manorama.SpringProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manorama.SpringProject.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findAllByuserId(long id);

	@Query("from Cart c where c.userId = :user_id and c.item_id = :item_id")
	Optional<Cart> findByUserIdandItemId(@Param("user_id") long user_id, @Param("item_id") long item_id);
}
