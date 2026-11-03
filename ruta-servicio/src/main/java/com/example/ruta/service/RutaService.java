package com.example.ruta.service;

import com.example.ruta.model.Ruta;
import com.example.ruta.repository.RutaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RutaService {

    private final RutaRepository rutaRepository;

    public RutaService(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

    public Ruta crear(Ruta ruta) {
        ruta.setIdRuta(null);
        ruta.setFechaAsignacion(LocalDateTime.now());
        return rutaRepository.save(ruta);
    }

    public List<Ruta> listar() {
        return rutaRepository.findAll();
    }

    public Ruta buscarPorId(Integer id) {
        return rutaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Ruta no encontrada con id " + id));
    }
}