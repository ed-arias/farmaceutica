package com.edu.javeriana.farmaceutica.api;

import java.util.List;

import com.edu.javeriana.farmaceutica.models.ServicioModel;
import com.edu.javeriana.farmaceutica.services.ServicioService;

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
public class ServicioController {

    private final ServicioService servicioService;

    @PostMapping("/proveedores/{id}/servicios")
    public ResponseEntity<ServicioModel> crearServicio(@PathVariable Long id, @RequestBody ServicioModel servicioModel)
            throws Exception {
        return new ResponseEntity<>(servicioService.crearServicio(id, servicioModel), HttpStatus.OK);
    }

    @GetMapping("/proveedores/{id}/servicios")
    public ResponseEntity<List<ServicioModel>> obtenerListaServicios(@PathVariable Long id)
            throws Exception {
        return new ResponseEntity<>(servicioService.obtenerListaServicios(id), HttpStatus.OK);
    }

    @GetMapping("/proveedores/{idProveedor}/servicios/{idServicio}")
    public ResponseEntity<ServicioModel> obtenerServicios(@PathVariable Long idProveedor, @PathVariable Long idServicio)
            throws Exception {
        return new ResponseEntity<>(servicioService.obtenerServicio(idServicio), HttpStatus.OK);
    }

    @PutMapping("/proveedores/{idProveedor}/servicios/{idServicio}")
    public ResponseEntity<ServicioModel> actualizarServicios(@PathVariable Long idProveedor, @PathVariable Long idServicio, @RequestBody ServicioModel servicioModel)
            throws Exception {
        return new ResponseEntity<>(servicioService.actualizarServicio(idServicio, servicioModel), HttpStatus.OK);
    }
    
}
