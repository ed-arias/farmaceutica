package com.edu.javeriana.farmaceutica;

import com.edu.javeriana.farmaceutica.clienteERP.ErpClient;
import com.edu.javeriana.farmaceutica.models.ClienteModel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FarmaceuticaApplicationTests {

	@Autowired
	private ErpClient client;

	@Test
	void contextLoads() throws Exception {
		ClienteModel clienteModel = new ClienteModel();
		clienteModel.setContrasena("contrasena");
		clienteModel.setDireccion("direccion");
		clienteModel.setNit("nit");
		clienteModel.setRazonSocial("razonSocial");
		client.lanzarSolicitud(clienteModel);
	}

}
