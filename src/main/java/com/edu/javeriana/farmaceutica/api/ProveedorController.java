package com.edu.javeriana.farmaceutica.api;

import java.util.List;

import com.edu.javeriana.farmaceutica.models.ProveedorModel;
import com.edu.javeriana.farmaceutica.services.ProveedorService;

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
public class ProveedorController {

    private final ProveedorService proveedorService;
    
    @PostMapping("/proveedores")
    public ResponseEntity<ProveedorModel> crearCliente(@RequestBody ProveedorModel proveedorModel){
        return new ResponseEntity<>(proveedorService.crearProveedor(proveedorModel), HttpStatus.OK);
    }

    @GetMapping("/proveedores/{id}")
    public ResponseEntity<ProveedorModel> obtenerCliente(@PathVariable Long id) throws Exception{
        return new ResponseEntity<>(proveedorService.obtenerProveedor(id), HttpStatus.OK);
    }

    @GetMapping("/proveedores")
    public ResponseEntity<List<ProveedorModel>> obtenerListaClientes() {
        return new ResponseEntity<>(proveedorService.obtenerListaProveedores(), HttpStatus.OK);
    }

    @PutMapping("/proveedores/{id}")
    public ResponseEntity<ProveedorModel> actualizarCliente(@PathVariable Long id, @RequestBody ProveedorModel proveedorModel) throws Exception {
        return new ResponseEntity<>(proveedorService.actualizarProveedor(id, proveedorModel), HttpStatus.OK);
    }


}
