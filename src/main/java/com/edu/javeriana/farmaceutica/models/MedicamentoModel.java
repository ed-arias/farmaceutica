package com.edu.javeriana.farmaceutica.models;

import lombok.Data;

@Data
public class MedicamentoModel {
    
    private Long id;
    private String nombre;
    private String descripcion;
    private Float precioUnitario;
}
