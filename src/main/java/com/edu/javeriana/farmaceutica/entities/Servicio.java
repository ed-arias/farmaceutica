package com.edu.javeriana.farmaceutica.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Servicio implements Serializable {

    private static final long serialVersionUID = -6164428785786797738L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idServicio;
    private String zipOrigen;
    private String zipDestino;
    private Float costo;
    private Integer diasParaEntrega;

    @OneToOne
    @JoinColumn(name = "fk_proveedor")
    private Proveedor proveedor;
}
