package com.example.ruta.controller;

import com.example.ruta.model.Ruta;
import com.example.ruta.service.RutaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rutas")
public class RutaController {

    private final RutaService rutaService;

    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ruta crear(@RequestBody Ruta ruta) {
        return rutaService.crear(ruta);
    }

    @GetMapping
    public List<Ruta> listar() {
        return rutaService.listar();
    }

    @GetMapping("/{id}")
    public Ruta buscarPorId(@PathVariable Integer id) {
        return rutaService.buscarPorId(id);
    }
}