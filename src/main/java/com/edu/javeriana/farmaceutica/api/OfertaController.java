package com.edu.javeriana.farmaceutica.api;

import com.edu.javeriana.farmaceutica.models.OfertaModel;
import com.edu.javeriana.farmaceutica.services.OfertaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class OfertaController {

    private final OfertaService ofertaService;

    @PostMapping("/proveedores/{id}/ofertas")
    public ResponseEntity<OfertaModel> registrarOferta(@PathVariable Long id, @RequestBody OfertaModel ofertaModel) {
        return new ResponseEntity<>(ofertaService.crearOferta(id, ofertaModel), HttpStatus.OK);
    }

}
