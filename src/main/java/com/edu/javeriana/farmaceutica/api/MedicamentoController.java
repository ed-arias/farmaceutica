package com.edu.javeriana.farmaceutica.api;

import java.util.List;

import com.edu.javeriana.farmaceutica.models.MedicamentoModel;
import com.edu.javeriana.farmaceutica.services.MedicamentoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;
    
    @GetMapping("/medicamentos/{id}")
    public ResponseEntity<MedicamentoModel> obtenerMedicamento(@PathVariable Long id){
        return new ResponseEntity<>(medicamentoService.obtenerMedicamento(id), HttpStatus.OK);
    }

    @GetMapping("/medicamentos")
    public ResponseEntity<List<MedicamentoModel>> obtenerListaMedicamento(@PathVariable Long id){
        return new ResponseEntity<>(medicamentoService.obtenerListaMedicamentos(), HttpStatus.OK);
    }
    
}
