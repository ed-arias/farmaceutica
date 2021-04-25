package com.edu.javeriana.farmaceutica.services;

import java.util.ArrayList;
import java.util.List;

import com.edu.javeriana.farmaceutica.entities.Proveedor;
import com.edu.javeriana.farmaceutica.entities.Servicio;
import com.edu.javeriana.farmaceutica.models.ServicioModel;
import com.edu.javeriana.farmaceutica.repositories.ProveedorRepository;
import com.edu.javeriana.farmaceutica.repositories.ServicioRepository;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class ServicioService {

    private final ProveedorRepository proveedorRepository;
    private final ServicioRepository servicioRepository;
    
    public ServicioModel crearServicio(Long idProveedor, ServicioModel servicioModel) throws Exception{

        Servicio servicio = new Servicio();

        Proveedor proveedor = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new Exception("Proveedor no encontrado con id: " + idProveedor));

        servicio.setCosto(servicioModel.getCosto());
        servicio.setDiasParaEntrega(servicioModel.getDiasParaEntrega());
        servicio.setProveedor(proveedor);
        servicio.setZipDestino(servicioModel.getZipDestino());
        servicio.setZipOrigen(servicioModel.getZipOrigen());

        servicio = servicioRepository.save(servicio);

        servicioModel.setIdServicio(servicio.getIdServicio());

        return servicioModel;
    }

    public ServicioModel obtenerServicio(Long idServicio){

        Servicio servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new Error("Servicio no encontrado con id: " + idServicio));

        ServicioModel servicioModel = new ServicioModel();

        servicioModel.setCosto(servicio.getCosto());
        servicioModel.setDiasParaEntrega(servicio.getDiasParaEntrega());
        servicioModel.setIdServicio(servicio.getIdServicio());
        servicioModel.setZipDestino(servicio.getZipDestino());
        servicioModel.setZipOrigen(servicio.getZipOrigen());

        return servicioModel;

    }

    public ServicioModel actualizarServicio(Long idServicio, ServicioModel servicioModel){

        Servicio servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new Error("Servicio no encontrado con id: " + idServicio));

        servicio = mapearServicio(servicioModel);

        servicioRepository.save(servicio);

        servicioModel = mapearServicioModel(servicio);

        return servicioModel;

    }

    public List<ServicioModel> obtenerListaServicios(Long idProveedor) throws Exception{

        Proveedor proveedor = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new Exception("Proveedor no encontrado con id: " + idProveedor));

        List<ServicioModel> serviciosModel = new ArrayList<>();
        List<Servicio> servicios = proveedor.getServicios();

        for(Servicio servicio : servicios){
            ServicioModel servicioModel = mapearServicioModel(servicio);
            serviciosModel.add(servicioModel);
        }

        return serviciosModel;

    }

    private ServicioModel mapearServicioModel(Servicio servicio){
        ServicioModel servicioModel = new ServicioModel();
        servicioModel.setCosto(servicio.getCosto());
        servicioModel.setDiasParaEntrega(servicio.getDiasParaEntrega());
        servicioModel.setIdServicio(servicio.getIdServicio());
        servicioModel.setZipDestino(servicio.getZipDestino());
        servicioModel.setZipOrigen(servicio.getZipOrigen());
        return servicioModel;
    }

    private Servicio mapearServicio(ServicioModel servicioModel){
        Servicio servicio = new Servicio();
        servicioModel.setCosto(servicio.getCosto());
        servicioModel.setDiasParaEntrega(servicio.getDiasParaEntrega());
        servicioModel.setIdServicio(servicio.getIdServicio());
        servicioModel.setZipDestino(servicio.getZipDestino());
        servicioModel.setZipOrigen(servicio.getZipOrigen());
        return servicio;
    }
}
