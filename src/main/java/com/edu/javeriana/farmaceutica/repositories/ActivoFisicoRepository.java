package com.edu.javeriana.farmaceutica.repositories;

import java.util.Optional;

import com.edu.javeriana.farmaceutica.entities.ActivoFisico;
import com.edu.javeriana.farmaceutica.entities.Medicamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivoFisicoRepository extends JpaRepository<ActivoFisico, Long> {
    
    public Optional<ActivoFisico> findByMedicamento(Medicamento medicamento);
}
