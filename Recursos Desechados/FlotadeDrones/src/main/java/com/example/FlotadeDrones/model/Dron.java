package com.example.FlotadeDrones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDron;

    private String modelo;
    private double capacidadMaxima;
    private int bateria;

    @Enumerated(EnumType.STRING)
    private EstadoDron estado;
}