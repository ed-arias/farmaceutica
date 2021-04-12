package com.edu.javeriana.farmaceutica.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idItem;
    private Integer cantidadSolicitada;
    private Float precioTotal;

    @OneToOne
    @JoinColumn(name = "fk_medicamento")
    private Medicamento medicamento;

    @ManyToOne
    @JoinColumn(name = "fk_pedido")
    private Pedido pedido;
    
}
