package com.edu.javeriana.farmaceutica.repositories;

import com.edu.javeriana.farmaceutica.entities.Medicamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    
}
