package com.manorama.SpringProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manorama.SpringProject.entities.Items;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {

	public List<Items> findAllByCategory(String category);

}
