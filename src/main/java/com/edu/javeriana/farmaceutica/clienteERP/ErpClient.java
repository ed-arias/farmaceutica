package com.edu.javeriana.farmaceutica.clienteERP;

import java.net.URI;
import java.net.URISyntaxException;

import com.edu.javeriana.farmaceutica.models.ClienteModel;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ErpClient {
    
    public void lanzarSolicitud(ClienteModel model) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        final String url = "https://farmaceutica-erp.herokuapp.com/api/v1/erp/clientes";
        URI uri = null;
        try{
            uri = new URI(url);
        }
        catch (URISyntaxException e){
            e.printStackTrace();
        }

        Object result = null;
        try{
			result = restTemplate.postForObject(uri, model, Object.class);
		}
		catch(HttpClientErrorException e){
			throw new Exception(e.fillInStackTrace());
		}

        if(result != null){
            log.info("Cliente registrado con exito en ERP");
        }
    }
    
}