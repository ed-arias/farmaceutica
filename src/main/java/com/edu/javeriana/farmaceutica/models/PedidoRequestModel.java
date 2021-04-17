package com.edu.javeriana.farmaceutica.models;

import java.util.List;

import lombok.Data;

@Data
public class PedidoRequestModel {
    
    private String direccionEntrega;
    private String zipDestino;
    private List<ItemModelRequest> items;
}
