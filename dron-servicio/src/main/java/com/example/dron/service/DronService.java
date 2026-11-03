package com.example.dron.service;

import com.example.dron.model.Dron;
import com.example.dron.repository.DronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DronService {

    private final DronRepository dronRepository;

    public Dron crear(Dron dron) {
        dron.setIdDron(null);
        return dronRepository.save(dron);
    }

    public List<Dron> listar() {
        return dronRepository.findAll();
    }

    public Dron buscarPorId(Integer id) {
        return dronRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Dron no encontrado con id " + id));
    }

    public Dron actualizar(Integer id, Dron datos) {
        Dron dron = buscarPorId(id);
        dron.setModelo(datos.getModelo());
        dron.setCapacidadMaxima(datos.getCapacidadMaxima());
        dron.setBateria(datos.getBateria());
        dron.setEstado(datos.getEstado());
        return dronRepository.save(dron);
    }

    public void eliminar(Integer id) {
        buscarPorId(id);
        dronRepository.deleteById(id);
    }
}