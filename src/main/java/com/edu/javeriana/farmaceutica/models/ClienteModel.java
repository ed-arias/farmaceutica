package com.edu.javeriana.farmaceutica.models;

import lombok.Data;

@Data
public class ClienteModel {
    
    private Long id;
    private String nit;
    private String razonSocial;
    private String direccion;
    private String contrasena;
    private String email;
}
