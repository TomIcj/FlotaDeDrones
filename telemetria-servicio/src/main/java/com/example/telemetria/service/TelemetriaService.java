package com.example.telemetria.service;

import com.example.telemetria.model.Telemetria;
import com.example.telemetria.repository.TelemetriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelemetriaService {

    private final TelemetriaRepository telemetriaRepository;

    public Telemetria registrar(Telemetria telemetria) {
        telemetria.setIdTelemetria(null);
        telemetria.setFechaRegistro(LocalDateTime.now());
        return telemetriaRepository.save(telemetria);
    }

    public List<Telemetria> listar() {
        return telemetriaRepository.findAll();
    }

    public Telemetria buscarPorId(Integer id) {
        return telemetriaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Telemetría no encontrada con id " + id));
    }

    public List<Telemetria> listarPorDron(Integer idDron) {
        return telemetriaRepository.findByIdDron(idDron);
    }

    public void eliminar(Integer id) {
        buscarPorId(id);
        telemetriaRepository.deleteById(id);
    }
}