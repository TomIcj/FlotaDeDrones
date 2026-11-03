package com.example.ruta.dto;

public record AsignacionRequest(Integer idPedido, Integer idDron, String puntosRecorrido, double distanciaKm) {
}