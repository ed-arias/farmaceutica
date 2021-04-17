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
public class ActivoFisico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idActivoFisico;
    private Integer cantidadEnExistencia;
    private Boolean disponible;
    
    @OneToOne
    @JoinColumn(name = "fk_medicamento")
    private Medicamento medicamento;

    @ManyToOne
    @JoinColumn(name = "fk_inventario")
    private Inventario inventario;

    public Boolean estaDisponible() {
        return this.cantidadEnExistencia > 0;
    }

    public Integer reducirCantidadExistencia(Integer cantidadSolicitada) {
        if(this.cantidadEnExistencia - cantidadSolicitada <= 0){
            this.cantidadEnExistencia = 0;
        }
        this.cantidadEnExistencia = this.cantidadEnExistencia - cantidadSolicitada;
        return this.cantidadEnExistencia;
    }
}
