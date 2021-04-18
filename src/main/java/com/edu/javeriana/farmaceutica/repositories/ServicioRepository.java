package com.edu.javeriana.farmaceutica.repositories;

import com.edu.javeriana.farmaceutica.entities.Servicio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    
}
