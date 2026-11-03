package com.example.dron.controller;

import com.example.dron.model.Dron;
import com.example.dron.service.DronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drones")
@RequiredArgsConstructor
public class DronController {

    private final DronService dronService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dron crear(@RequestBody Dron dron) {
        return dronService.crear(dron);
    }

    @GetMapping
    public List<Dron> listar() {
        return dronService.listar();
    }

    @GetMapping("/{id}")
    public Dron buscarPorId(@PathVariable Integer id) {
        return dronService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Dron actualizar(@PathVariable Integer id, @RequestBody Dron dron) {
        return dronService.actualizar(id, dron);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        dronService.eliminar(id);
    }
}