package com.manorama.SpringProject.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.manorama.SpringProject.Summary.DailySummary;
import com.manorama.SpringProject.Summary.MonthlySummary;
import com.manorama.SpringProject.entities.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
//	@Query("SELECT new com.manorama.SpringProject.Summary.MonthlySummary(e.user_id, MONTH(e.date),  CAST(SUM(e.amount) AS float)) FROM Orders e WHERE e.user_id = ?1 AND MONTH(CURDATE()) = MONTH(e.date) GROUP BY e.user_id, MONTH(e.date)")
//	MonthlySummary getUserMonthlySummary(long id);
//
//	@Query("SELECT new com.manorama.SpringProject.Summary.DailySummary(e.user_id, DAY(e.date),  CAST(SUM(e.amount) AS float)) FROM Orders e WHERE e.user_id = ?1 AND DAY(CURDATE()) = DAY(e.date) GROUP BY e.user_id, DAY(e.date)")
//	DailySummary getUserDailySummary(long id);

	@Query("SELECT new com.manorama.SpringProject.Summary.MonthlySummary(MONTH(o.date) AS month, YEAR(o.date) AS year, SUM(oi.quantity * i.price) AS total_amount, o.user_id) FROM Orders o JOIN OrderItems oi ON o.id = oi.orders.id JOIN Items i ON oi.items.id = i.id WHERE MONTH(o.date) = MONTH(CURDATE()) AND YEAR(o.date) = YEAR(CURDATE()) AND o.user_id=:user_id GROUP BY MONTH(o.date), YEAR(o.date)")
	MonthlySummary userSummary(@Param("user_id") long user_id);
	
	@Query("SELECT new com.manorama.SpringProject.Summary.DailySummary(DAY(o.date) as day, MONTH(o.date) AS month, YEAR(o.date) AS year, SUM(oi.quantity * i.price) AS total_amount, o.user_id) FROM Orders o JOIN OrderItems oi ON o.id = oi.orders.id JOIN Items i ON oi.items.id = i.id WHERE DAY(o.date) = DAY(CURDATE()) AND MONTH(o.date) = MONTH(CURDATE()) AND YEAR(o.date) = YEAR(CURDATE()) AND o.user_id=:user_id GROUP BY MONTH(o.date), YEAR(o.date)")
	DailySummary userDailySummary(@Param("user_id") long user_id);
	
//	Orders findAllByDate(LocalDate date);
	
	@Query("from Orders s where DATE(s.date) = :date")
	public List<Orders> findAllByDate(@Param("date") Date date);

}
