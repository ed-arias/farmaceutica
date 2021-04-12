package com.edu.javeriana.farmaceutica.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Medicamento implements Serializable{
    
    private static final long serialVersionUID = 7394520723710345135L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMedicamento;
    private String nombre;
    private String descripcion;
    private Float precioUnitario;
    
}
