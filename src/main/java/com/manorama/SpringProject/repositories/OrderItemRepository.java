package com.manorama.SpringProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manorama.SpringProject.entities.OrderItems;
import com.manorama.SpringProject.entities.Orders;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {

	List<OrderItems> findAllByOrders(Orders orders);
	void deleteAllByOrders(Orders orders);
	
	
}
