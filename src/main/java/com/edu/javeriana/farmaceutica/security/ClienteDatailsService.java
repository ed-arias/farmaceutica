package com.edu.javeriana.farmaceutica.security;

import com.edu.javeriana.farmaceutica.entities.User;
import com.edu.javeriana.farmaceutica.repositories.ClienteRespository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteDatailsService implements UserDetailsService {

    private final ClienteRespository clienteRespository;

    @Override
    public UserDetails loadUserByUsername(String s) {
        User user = clienteRespository.findByNit(s);
        if(user == null){
            user = new User(){

                @Override
                public String getContrasena() {
                    return "";
                }

                @Override
                public String getNit() {
                    return "";
                }

                @Override
                public Boolean getActivo() {
                    return true;
                }

                @Override
                public String getRol() {
                    return "";
                }
                
            };
        }
        UserMain userMain = new UserMain(user);
        return userMain;
    }
    
}
