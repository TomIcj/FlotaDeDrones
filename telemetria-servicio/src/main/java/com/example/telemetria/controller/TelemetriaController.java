package com.example.telemetria.controller;

import com.example.telemetria.model.Telemetria;
import com.example.telemetria.service.TelemetriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telemetrias")
@RequiredArgsConstructor
public class TelemetriaController {

    private final TelemetriaService telemetriaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Telemetria registrar(@RequestBody Telemetria telemetria) {
        return telemetriaService.registrar(telemetria);
    }

    @GetMapping
    public List<Telemetria> listar() {
        return telemetriaService.listar();
    }

    @GetMapping("/{id}")
    public Telemetria buscarPorId(@PathVariable Integer id) {
        return telemetriaService.buscarPorId(id);
    }

    @GetMapping("/dron/{idDron}")
    public List<Telemetria> listarPorDron(@PathVariable Integer idDron) {
        return telemetriaService.listarPorDron(idDron);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        telemetriaService.eliminar(id);
    }
}