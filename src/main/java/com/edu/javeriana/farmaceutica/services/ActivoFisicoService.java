package com.edu.javeriana.farmaceutica.services;

import java.util.ArrayList;
import java.util.List;

import com.edu.javeriana.farmaceutica.entities.ActivoFisico;
import com.edu.javeriana.farmaceutica.entities.Medicamento;
import com.edu.javeriana.farmaceutica.models.ActivoFisicoModel;
import com.edu.javeriana.farmaceutica.repositories.ActivoFisicoRepository;
import com.edu.javeriana.farmaceutica.repositories.MedicamentoRepository;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class ActivoFisicoService {

    private final ActivoFisicoRepository activoFisicoRepository;
    private final MedicamentoRepository medicamentoRepository;

    public ActivoFisicoModel crearActivoFisico(ActivoFisicoModel activoFisicoModel) {

        ActivoFisico activoFisico = new ActivoFisico();

        activoFisico.setCantidadEnExistencia(activoFisicoModel.getCantidadDisponible());

        Medicamento medicamento = medicamentoRepository.findById(activoFisicoModel.getIdMedicamento()).orElseThrow(
                () -> new Error("Medicamento no encontrado con id: " + activoFisicoModel.getIdMedicamento()));

        activoFisico.setMedicamento(medicamento);

        activoFisico = activoFisicoRepository.save(activoFisico);

        activoFisicoModel.setId(activoFisico.getIdActivoFisico());

        return activoFisicoModel;
    }

    public ActivoFisicoModel obtenerActivoFisico(Long id) {
        ActivoFisico activoFisico = activoFisicoRepository.findById(id)
                .orElseThrow(() -> new Error("Activo físico no encontrado con id: " + id));

        ActivoFisicoModel activoFisicoModel = new ActivoFisicoModel();
        activoFisicoModel.setCantidadDisponible(activoFisico.getCantidadEnExistencia());
        activoFisicoModel.setId(activoFisico.getIdActivoFisico());
        activoFisicoModel.setIdMedicamento(activoFisico.getMedicamento().getIdMedicamento());

        return activoFisicoModel;
    }

    public List<ActivoFisicoModel> obtenerListaActivosFisicos() {

        List<ActivoFisico> activosFisicos = activoFisicoRepository.findAll();
        List<ActivoFisicoModel> activosFisicosModel = new ArrayList<>();

        for (ActivoFisico activoFisico : activosFisicos) {
            ActivoFisicoModel activoFisicoModel = new ActivoFisicoModel();
            activoFisicoModel.setCantidadDisponible(activoFisico.getCantidadEnExistencia());
            activoFisicoModel.setId(activoFisico.getIdActivoFisico());
            activoFisicoModel.setIdMedicamento(activoFisico.getMedicamento().getIdMedicamento());
            activosFisicosModel.add(activoFisicoModel);
        }

        return activosFisicosModel;
    }

    public Boolean verificarDisponibilidad(Medicamento medicamento) throws Exception {

        ActivoFisico activoFisico = activoFisicoRepository.findByMedicamento(medicamento)
                .orElseThrow(() -> new Exception(
                        "Medicamento \"" + medicamento.getNombre() + "\" no encontrado entre los activos físicos"));

        return activoFisico.estaDisponible();
    }

    public Integer verificarCantidadEnExistencia(Medicamento medicamento, Integer cantidadSolicitada) throws Exception {

        Integer cantidadDisponible = cantidadSolicitada;
        ActivoFisico activoFisico = activoFisicoRepository.findByMedicamento(medicamento)
                .orElseThrow(() -> new Exception(
                        "Medicamento \"" + medicamento.getNombre() + "\" no encontrado entre los activos físicos"));

        if (activoFisico.getCantidadEnExistencia() < cantidadSolicitada) {
            cantidadDisponible = activoFisico.getCantidadEnExistencia();
        }

        activoFisico.reducirCantidadExistencia(cantidadDisponible);
        activoFisicoRepository.save(activoFisico);

        return cantidadDisponible;
    }

}
