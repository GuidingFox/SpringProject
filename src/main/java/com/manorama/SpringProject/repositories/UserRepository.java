package com.manorama.SpringProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manorama.SpringProject.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
