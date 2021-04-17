package com.edu.javeriana.farmaceutica.api;

import java.util.List;

import com.edu.javeriana.farmaceutica.models.ActivoFisicoModel;
import com.edu.javeriana.farmaceutica.services.ActivoFisicoService;

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
public class ActivoFisicoController {

    private final ActivoFisicoService activoFisicoService;
    
    @PostMapping("/activos")
    public ResponseEntity<ActivoFisicoModel> crearActivoFisico(@RequestBody ActivoFisicoModel activoFisicoRequest){
        return new ResponseEntity<>(activoFisicoService.crearActivoFisico(activoFisicoRequest), HttpStatus.OK);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ActivoFisicoModel>> obtenerListaActivosFisicos(){
        return new ResponseEntity<>(activoFisicoService.obtenerListaActivosFisicos(), HttpStatus.OK);
    }

    @GetMapping("/activos/{id}")
    public ResponseEntity<ActivoFisicoModel> obtenerActivoFisico(@PathVariable Long id){
        return new ResponseEntity<>(activoFisicoService.obtenerActivoFisico(id), HttpStatus.OK);
    }
}
