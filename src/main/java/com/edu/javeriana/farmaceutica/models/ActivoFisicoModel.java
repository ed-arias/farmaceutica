package com.edu.javeriana.farmaceutica.models;

import lombok.Data;

@Data
public class ActivoFisicoModel {
    
    private Long id;
    private Long idMedicamento;
    private Integer cantidadDisponible;
}
