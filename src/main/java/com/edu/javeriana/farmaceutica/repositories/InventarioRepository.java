package com.edu.javeriana.farmaceutica.repositories;

import com.edu.javeriana.farmaceutica.entities.Inventario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    
}
