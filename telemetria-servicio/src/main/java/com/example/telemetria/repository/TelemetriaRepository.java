package com.example.telemetria.repository;

import com.example.telemetria.model.Telemetria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelemetriaRepository extends JpaRepository<Telemetria, Integer> {

    List<Telemetria> findByIdDron(Integer idDron);
}