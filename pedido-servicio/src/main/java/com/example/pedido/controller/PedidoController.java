package com.example.pedido.controller;

import com.example.pedido.model.EstadoPedido;
import com.example.pedido.model.Pedido;
import com.example.pedido.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido crear(@RequestBody Pedido pedido) {
        return pedidoService.crear(pedido);
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listar();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Integer id) {
        return pedidoService.buscarPorId(id);
    }

    @PatchMapping("/{id}/estado")
    public Pedido cambiarEstado(@PathVariable Integer id, @RequestParam EstadoPedido estado) {
        return pedidoService.cambiarEstado(id, estado);
    }
}