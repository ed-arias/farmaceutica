package com.edu.javeriana.farmaceutica.models;

import lombok.Data;

@Data
public class OfertaModel {
    
    private Long id;
    private Long idPedido;
    private Float costo;
    private Integer diasParaEntrega;
}
