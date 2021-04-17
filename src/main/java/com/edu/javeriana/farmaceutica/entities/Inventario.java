package com.edu.javeriana.farmaceutica.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idInventario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventario")
    private List<ActivoFisico> activosFisicos = new ArrayList<>();

    public void agregarActivoFisico(ActivoFisico activoFisico){
        activoFisico.setInventario(this);
        this.activosFisicos.add(activoFisico);
    }
}
