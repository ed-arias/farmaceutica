package com.edu.javeriana.farmaceutica.services;

import java.util.ArrayList;
import java.util.List;

import com.edu.javeriana.farmaceutica.entities.Proveedor;
import com.edu.javeriana.farmaceutica.models.ProveedorModel;
import com.edu.javeriana.farmaceutica.repositories.ProveedorRepository;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class ProveedorService {
    
    private final ProveedorRepository proveedorRepository;

    public ProveedorModel crearProveedor(ProveedorModel proveedorModel) {
        Proveedor proveedor = new Proveedor();
        proveedor.setDireccion(proveedorModel.getDireccion());
        proveedor.setNit(proveedorModel.getNit());
        proveedor.setRazonSocial(proveedorModel.getRazonSocial());

        proveedor = proveedorRepository.save(proveedor);

        proveedorModel.setId(proveedor.getIdProveedor());

        return proveedorModel;
    }

    public ProveedorModel obtenerProveedor(Long id) throws Exception {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new Exception("Proveedor no encontrado con id: " + id));

        ProveedorModel proveedorModel = new ProveedorModel();
        proveedorModel.setDireccion(proveedor.getDireccion());
        proveedorModel.setId(proveedor.getIdProveedor());
        proveedorModel.setNit(proveedor.getNit());
        proveedorModel.setRazonSocial(proveedor.getRazonSocial());

        return proveedorModel;
    }

    public List<ProveedorModel> obtenerListaProveedores() {
        
        List<Proveedor> proveedores = proveedorRepository.findAll();
        List<ProveedorModel> proveedoresModel = new ArrayList<>();

        for (Proveedor proveedor : proveedores) {
            ProveedorModel proveedorModel = new ProveedorModel();
            proveedorModel.setDireccion(proveedor.getDireccion());
            proveedorModel.setId(proveedor.getIdProveedor());
            proveedorModel.setNit(proveedor.getNit());
            proveedorModel.setRazonSocial(proveedor.getRazonSocial());

            proveedoresModel.add(proveedorModel);
        }

        return proveedoresModel;
    }

    public ProveedorModel actualizarProveedor(Long id, ProveedorModel proveedorModel) throws Exception {
        
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new Exception("Proveedor no encontrado con id: " + id));

        proveedor.setDireccion(proveedorModel.getDireccion());
        proveedor.setNit(proveedorModel.getNit());
        proveedor.setRazonSocial(proveedorModel.getRazonSocial());

        proveedor = proveedorRepository.save(proveedor);

        proveedorModel.setDireccion(proveedor.getDireccion());
        proveedorModel.setId(proveedor.getIdProveedor());
        proveedorModel.setNit(proveedor.getNit());
        proveedorModel.setRazonSocial(proveedor.getRazonSocial());

        return proveedorModel;
    }
}
