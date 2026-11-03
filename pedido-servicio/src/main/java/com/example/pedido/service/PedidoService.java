package com.example.pedido.service;

import com.example.pedido.model.EstadoPedido;
import com.example.pedido.model.Pedido;
import com.example.pedido.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private static final String CLIENTE_URL = "http://localhost:8081/clientes/";

    private final PedidoRepository pedidoRepository;
    private final RestClient restClient = RestClient.create();

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido crear(Pedido pedido) {
        validarCliente(pedido.getIdCliente());
        pedido.setIdPedido(null);
        pedido.setEstado(EstadoPedido.PENDIENTE);
        pedido.setFechaCreacion(LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pedido no encontrado con id " + id));
    }

    public Pedido cambiarEstado(Integer id, EstadoPedido estado) {
        Pedido pedido = buscarPorId(id);
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }

    private void validarCliente(Integer idCliente) {
        if (idCliente == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El idCliente es obligatorio");
        }
        try {
            restClient.get()
                    .uri(CLIENTE_URL + idCliente)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cliente no encontrado con id " + idCliente);
        } catch (ResourceAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, "cliente-servicio no está disponible");
        }
    }
}