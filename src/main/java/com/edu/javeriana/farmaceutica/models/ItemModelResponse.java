package com.edu.javeriana.farmaceutica.models;

import lombok.Data;

@Data
public class ItemModelResponse {
    
    private Long idMedicamento;
    private String nombre;
    private Float precioUnitario;
    private Integer cantidadSolicitada;
    private Float totalItem;
}
