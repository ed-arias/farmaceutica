package com.edu.javeriana.farmaceutica.services;

import java.util.ArrayList;
import java.util.List;

import com.edu.javeriana.farmaceutica.entities.Medicamento;
import com.edu.javeriana.farmaceutica.models.MedicamentoModel;
import com.edu.javeriana.farmaceutica.repositories.MedicamentoRepository;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoModel obtenerMedicamento(Long id) {

        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new Error("Medicamento no encontrado con id: " + id));

        MedicamentoModel medicamentoModel = new MedicamentoModel();
        medicamentoModel.setDescripcion(medicamento.getDescripcion());
        medicamentoModel.setId(medicamento.getIdMedicamento());
        medicamentoModel.setNombre(medicamento.getNombre());
        medicamentoModel.setPrecioUnitario(medicamento.getPrecioUnitario());

        return medicamentoModel;
    }

    public List<MedicamentoModel> obtenerListaMedicamentos() {

        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        List<MedicamentoModel> medicamentosModel = new ArrayList<>();

        for (Medicamento medicamento : medicamentos) {
            MedicamentoModel medicamentoModel = new MedicamentoModel();
            medicamentoModel.setDescripcion(medicamento.getDescripcion());
            medicamentoModel.setId(medicamento.getIdMedicamento());
            medicamentoModel.setNombre(medicamento.getNombre());
            medicamentoModel.setPrecioUnitario(medicamento.getPrecioUnitario());

            medicamentosModel.add(medicamentoModel);
        }

        return medicamentosModel;
    }

    public MedicamentoModel crearMedicamento(MedicamentoModel medicamentoModel) {
        Medicamento medicamento = new Medicamento();

        medicamento.setDescripcion(medicamentoModel.getDescripcion());
        medicamento.setNombre(medicamentoModel.getNombre());
        medicamento.setPrecioUnitario(medicamentoModel.getPrecioUnitario());

        medicamento = medicamentoRepository.save(medicamento);

        medicamentoModel.setId(medicamento.getIdMedicamento());

        return medicamentoModel;
    }

}
