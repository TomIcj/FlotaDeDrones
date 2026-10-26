package com.example.FlotadeDrones.model;

import jakarta.persistence.Column;
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
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRuta;

    @Column(columnDefinition = "TEXT")
    private String puntosRecorrido;

    private double distanciaKm;
    private LocalDateTime fechaAsignacion;

    @ManyToOne
    @JoinColumn(name = "id_dron", nullable = false)
    private Dron dron;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;
}