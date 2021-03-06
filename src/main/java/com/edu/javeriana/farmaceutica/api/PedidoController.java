package com.edu.javeriana.farmaceutica.api;

import java.util.List;

import com.edu.javeriana.farmaceutica.models.OfertaModel;
import com.edu.javeriana.farmaceutica.models.PedidoModel;
import com.edu.javeriana.farmaceutica.models.PedidoRequestModel;
import com.edu.javeriana.farmaceutica.services.PedidoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping("/clientes/{id}/pedidos")
    public ResponseEntity<PedidoModel> crearPedido(@PathVariable Long id, @RequestBody PedidoRequestModel pedidoRequestModel)
            throws Exception {
        return new ResponseEntity<>(pedidoService.crearPedido(id, pedidoRequestModel), HttpStatus.OK);
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<PedidoModel> obtenerPedido(@PathVariable Long id)
            throws Exception {
        return new ResponseEntity<>(pedidoService.obtenerPedido(id), HttpStatus.OK);
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoModel>> obtenerListaPedidos()
            throws Exception {
        return new ResponseEntity<>(pedidoService.obtenerListaPedidos(), HttpStatus.OK);
    }

    @GetMapping("/pedidos/{id}/ofertas")
    public ResponseEntity<List<OfertaModel>> obtenerOfertasPorPedido(@PathVariable Long id)
            throws Exception {
        return new ResponseEntity<>(pedidoService.obtenerOfertasPorPedido(id), HttpStatus.OK);
    }

}
