package com.example.dron.repository;

import com.example.dron.model.Dron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DronRepository extends JpaRepository<Dron, Integer> {
}