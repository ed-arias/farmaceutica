package com.edu.javeriana.farmaceutica.models;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PedidoModel {
    
    private Long idPedido;
    private LocalDateTime fechaPedido;
    private List<ItemModelResponse> items;
    private Float totalPedido;
}
