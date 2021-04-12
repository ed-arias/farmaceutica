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
public class Oferta implements Serializable{
    
    private static final long serialVersionUID = -3898297838041385308L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOferta;

    private Float costo;
    private Integer diasParaEntrega;

    @ManyToOne
    @JoinColumn(name = "fk_pedido")
    private Pedido pedido;

    @OneToOne
    @JoinColumn(name = "fk_proveedor")
    private Proveedor proveedor;
}
