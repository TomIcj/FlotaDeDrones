package com.example.ruta.service;

import com.example.ruta.dto.AsignacionRequest;
import com.example.ruta.dto.DronDto;
import com.example.ruta.dto.PedidoDto;
import com.example.ruta.model.Ruta;
import com.example.ruta.repository.RutaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RutaService {

    private static final String PEDIDO_URL = "http://localhost:8082/pedidos/";
    private static final String DRON_URL = "http://localhost:8083/drones/";
    private static final int BATERIA_MINIMA = 30;

    private final RutaRepository rutaRepository;
    private final RestClient restClient = RestClient.create();

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

    public Ruta asignar(AsignacionRequest request) {
        if (request.idPedido() == null || request.idDron() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "idPedido e idDron son obligatorios");
        }

        PedidoDto pedido = obtenerPedido(request.idPedido());
        DronDto dron = obtenerDron(request.idDron());

        if (!"PENDIENTE".equals(pedido.estado())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El pedido no está pendiente");
        }
        if (!"DISPONIBLE".equals(dron.estado())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El dron no está disponible");
        }
        if (dron.bateria() < BATERIA_MINIMA) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El dron no tiene batería suficiente");
        }
        if (pedido.peso() > dron.capacidadMaxima()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El peso del pedido supera la capacidad del dron");
        }

        Ruta ruta = new Ruta();
        ruta.setIdPedido(request.idPedido());
        ruta.setIdDron(request.idDron());
        ruta.setPuntosRecorrido(request.puntosRecorrido());
        ruta.setDistanciaKm(request.distanciaKm());
        ruta.setFechaAsignacion(LocalDateTime.now());
        Ruta guardada = rutaRepository.save(ruta);

        cambiarEstado(PEDIDO_URL + request.idPedido() + "/estado?estado=EN_CAMINO", "pedido-servicio");
        cambiarEstado(DRON_URL + request.idDron() + "/estado?estado=EN_VUELO", "dron-servicio");

        return guardada;
    }

    private PedidoDto obtenerPedido(Integer id) {
        try {
            return restClient.get().uri(PEDIDO_URL + id).retrieve().body(PedidoDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pedido no encontrado con id " + id);
        } catch (ResourceAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, "pedido-servicio no está disponible");
        }
    }

    private DronDto obtenerDron(Integer id) {
        try {
            return restClient.get().uri(DRON_URL + id).retrieve().body(DronDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Dron no encontrado con id " + id);
        } catch (ResourceAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, "dron-servicio no está disponible");
        }
    }

    private void cambiarEstado(String url, String servicio) {
        try {
            restClient.patch().uri(url).retrieve().toBodilessEntity();
        } catch (ResourceAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, servicio + " no está disponible");
        }
    }
}