package com.manorama.SpringProject.repositories;

import com.manorama.SpringProject.Summary.DailySummary;
import com.manorama.SpringProject.Summary.MonthlySummary;
import com.manorama.SpringProject.entities.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
//	@Query("SELECT new com.manorama.SpringProject.Summary.MonthlySummary(e.user_id, MONTH(e.date),  CAST(SUM(e.amount) AS float)) FROM Orders e WHERE e.user_id = ?1 AND MONTH(CURDATE()) = MONTH(e.date) GROUP BY e.user_id, MONTH(e.date)")
//	MonthlySummary getUserMonthlySummary(long id);
//
//	@Query("SELECT new com.manorama.SpringProject.Summary.DailySummary(e.user_id, DAY(e.date),  CAST(SUM(e.amount) AS float)) FROM Orders e WHERE e.user_id = ?1 AND DAY(CURDATE()) = DAY(e.date) GROUP BY e.user_id, DAY(e.date)")
//	DailySummary getUserDailySummary(long id);
}
