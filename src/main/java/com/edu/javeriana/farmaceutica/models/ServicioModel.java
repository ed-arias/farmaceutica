package com.edu.javeriana.farmaceutica.models;

import lombok.Data;

@Data
public class ServicioModel {
    
    private Long idServicio;
    private String zipOrigen;
    private String zipDestino;
    private Float costo;
    private Integer diasParaEntrega;
}
