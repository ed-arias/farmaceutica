package com.edu.javeriana.farmaceutica.services;

import com.edu.javeriana.farmaceutica.entities.Oferta;
import com.edu.javeriana.farmaceutica.entities.Pedido;
import com.edu.javeriana.farmaceutica.entities.Proveedor;
import com.edu.javeriana.farmaceutica.models.OfertaModel;
import com.edu.javeriana.farmaceutica.repositories.OfertaRepository;
import com.edu.javeriana.farmaceutica.repositories.PedidoRepository;
import com.edu.javeriana.farmaceutica.repositories.ProveedorRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OfertaService {

    private ProveedorRepository proveedorRepository;
    private PedidoRepository pedidoRepository;
    private OfertaRepository ofertaRepository;

    public OfertaModel crearOferta(Long idProveedor, OfertaModel ofertaModel) {

        Proveedor proveedor = obtenerProveedor(idProveedor);
        Pedido pedido = obtenerPedido(ofertaModel.getIdPedido());
        Oferta oferta = mapearOferta(proveedor, pedido, ofertaModel);

        proveedor.agregarOferta(oferta);
        pedido.agregarOferta(oferta);

        oferta = ofertaRepository.save(oferta);

        ofertaModel.setId(oferta.getIdOferta());

        return ofertaModel;
    }

    private Oferta mapearOferta(Proveedor proveedor, Pedido pedido, OfertaModel ofertaModel) {
        Oferta oferta = new Oferta();
        oferta.setCosto(ofertaModel.getCosto());
        oferta.setDiasParaEntrega(ofertaModel.getDiasParaEntrega());
        oferta.setPedido(pedido);
        oferta.setProveedor(proveedor);

        return oferta;
    }

    private Pedido obtenerPedido(Long idPedido) {
        return pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new Error("Pedido no encontrado con id: " + idPedido));
    }

    private Proveedor obtenerProveedor(Long idProveedor) {
        return proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new Error("Proveedor no encontrado con id: " + idProveedor));
    }

}
