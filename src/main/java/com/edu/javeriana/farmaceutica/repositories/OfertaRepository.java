package com.edu.javeriana.farmaceutica.repositories;

import com.edu.javeriana.farmaceutica.entities.Oferta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    
}
