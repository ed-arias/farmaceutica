package com.edu.javeriana.farmaceutica.models;

import lombok.Data;

@Data
public class ItemModelRequest {
    
    private Long idMedicamento;
    private Integer cantidadSolicitada;
}
