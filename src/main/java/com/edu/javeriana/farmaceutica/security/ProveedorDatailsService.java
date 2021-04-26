package com.edu.javeriana.farmaceutica.security;

import com.edu.javeriana.farmaceutica.entities.User;
import com.edu.javeriana.farmaceutica.repositories.ProveedorRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProveedorDatailsService implements UserDetailsService {

    private final ProveedorRepository proveedorRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = proveedorRepository.findByNit(s);
        UserMain userMain = new UserMain(user);
        return userMain;
    }
    
}
