package com.edu.javeriana.farmaceutica.services;

import java.util.ArrayList;
import java.util.List;

import com.edu.javeriana.farmaceutica.entities.Oferta;
import com.edu.javeriana.farmaceutica.entities.Proveedor;
import com.edu.javeriana.farmaceutica.models.OfertaModel;
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

    public List<OfertaModel> obtenerOfertasPorProveedor(Long idProveedor) throws Exception{

        Proveedor proveedor = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new Exception("Proveedor no encontrado con id: " + idProveedor));
        
        List<OfertaModel> ofertasModel = new ArrayList<>();
        List<Oferta> ofertas = proveedor.getOfertas();

        for (Oferta oferta : ofertas) {
            OfertaModel ofertaModel = new OfertaModel();
            ofertaModel = mapearOfertaModel(oferta);
            ofertasModel.add(ofertaModel);
        }

        return ofertasModel;

    }

    private OfertaModel mapearOfertaModel(Oferta oferta) {
        OfertaModel ofertaModel = new OfertaModel();
        ofertaModel.setCosto(oferta.getCosto());
        ofertaModel.setDiasParaEntrega(oferta.getDiasParaEntrega());
        ofertaModel.setId(oferta.getIdOferta());
        ofertaModel.setIdPedido(oferta.getPedido().getIdPedido());
        ofertaModel.setProveedor(oferta.getProveedor().getRazonSocial());
        return ofertaModel;
    }
}
