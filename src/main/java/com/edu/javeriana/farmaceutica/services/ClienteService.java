package com.edu.javeriana.farmaceutica.services;

import java.util.ArrayList;
import java.util.List;

import com.edu.javeriana.farmaceutica.entities.Cliente;
import com.edu.javeriana.farmaceutica.models.ClienteModel;
import com.edu.javeriana.farmaceutica.repositories.ClienteRespository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class ClienteService {

    private final ClienteRespository clienteRespository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public ClienteModel crearCliente(ClienteModel clienteModel) {

        Cliente cliente = new Cliente();
        cliente.setDireccion(clienteModel.getDireccion());
        cliente.setNit(clienteModel.getNit());
        cliente.setRazonSocial(clienteModel.getRazonSocial());
        cliente.setContrasena(passwordEncoder.encode(clienteModel.getContrasena()));

        cliente = clienteRespository.save(cliente);

        clienteModel.setId(cliente.getIdCliente());

        mailService.sendSimpleMessage(clienteModel.getEmail(), "Nuevo registro en Farmaceutica Javeriana", "Muchas gracias por preferirnos, ahora puedes realizar pedidos en nuestra plataforma");

        return clienteModel;
    }

    public ClienteModel obtenerCliente(Long id) throws Exception {

        Cliente cliente = clienteRespository.findById(id)
                .orElseThrow(() -> new Exception("Cliente no encontrado con id: " + id));

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setDireccion(cliente.getDireccion());
        clienteModel.setId(cliente.getIdCliente());
        clienteModel.setNit(cliente.getNit());
        clienteModel.setRazonSocial(cliente.getRazonSocial());

        return clienteModel;
    }

    public List<ClienteModel> obtenerListaClientes() {

        List<Cliente> clientes = clienteRespository.findAll();
        List<ClienteModel> clientesModel = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteModel clienteModel = new ClienteModel();
            clienteModel.setDireccion(cliente.getDireccion());
            clienteModel.setId(cliente.getIdCliente());
            clienteModel.setNit(cliente.getNit());
            clienteModel.setRazonSocial(cliente.getRazonSocial());

            clientesModel.add(clienteModel);
        }

        return clientesModel;
    }

    public ClienteModel actualizarCliente(Long id, ClienteModel clienteModel) throws Exception {

        Cliente cliente = clienteRespository.findById(id)
                .orElseThrow(() -> new Exception("Cliente no encontrado con id: " + id));

        cliente.setDireccion(clienteModel.getDireccion());
        cliente.setNit(clienteModel.getNit());
        cliente.setRazonSocial(clienteModel.getRazonSocial());

        cliente = clienteRespository.save(cliente);

        clienteModel.setDireccion(cliente.getDireccion());
        clienteModel.setId(cliente.getIdCliente());
        clienteModel.setNit(cliente.getNit());
        clienteModel.setRazonSocial(cliente.getRazonSocial());

        return clienteModel;
    }

    public void eliminarCliente() {

    }

}
