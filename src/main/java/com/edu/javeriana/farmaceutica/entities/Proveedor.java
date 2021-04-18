package com.edu.javeriana.farmaceutica.entities;

import java.io.Serializable;
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
public class Proveedor implements Serializable {
    
    private static final long serialVersionUID = 873488609414567154L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProveedor;
    private String nit;
    private String razonSocial;
    private String direccion;
    private Boolean dadoDeAlta;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private List<Oferta> ofertas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private List<Servicio> servicios;

    public void agregarOferta(Oferta oferta){
        oferta.setProveedor(this);
        this.ofertas.add(oferta);
    }

    public void agregarServicio(Servicio servicio){
        servicio.setProveedor(this);
        this.servicios.add(servicio);
    }
}
