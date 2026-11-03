package com.example.dron.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDron;

    @Column(nullable = false)
    private String modelo;

    private double capacidadMaxima;

    private int bateria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoDron estado;
}