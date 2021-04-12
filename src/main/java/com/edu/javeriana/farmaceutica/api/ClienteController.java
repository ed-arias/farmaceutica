package com.edu.javeriana.farmaceutica.api;

import java.util.List;

import com.edu.javeriana.farmaceutica.models.ClienteModel;
import com.edu.javeriana.farmaceutica.services.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ClienteController {

    private final ClienteService clienteService;
    
    @PostMapping("/clientes")
    public ResponseEntity<ClienteModel> crearCliente(@RequestBody ClienteModel clienteModel){
        return new ResponseEntity<>(clienteService.crearCliente(clienteModel), HttpStatus.OK);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteModel> obtenerCliente(@PathVariable Long id) throws Exception{
        return new ResponseEntity<>(clienteService.obtenerCliente(id), HttpStatus.OK);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteModel>> obtenerListaClientes() {
        return new ResponseEntity<>(clienteService.obtenerListaClientes(), HttpStatus.OK);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<ClienteModel> actualizarCliente(@PathVariable Long id, @RequestBody ClienteModel clienteModel) throws Exception {
        return new ResponseEntity<>(clienteService.actualizarCliente(id, clienteModel), HttpStatus.OK);
    }


}
