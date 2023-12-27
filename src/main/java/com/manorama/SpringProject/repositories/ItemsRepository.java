package com.manorama.SpringProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manorama.SpringProject.models.Items;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {

}
