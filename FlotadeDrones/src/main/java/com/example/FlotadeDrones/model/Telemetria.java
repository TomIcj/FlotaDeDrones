package com.example.FlotadeDrones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Telemetria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTelemetria;

    private double latitud;
    private double longitud;
    private int bateria;
    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "id_dron", nullable = false)
    private Dron dron;
}