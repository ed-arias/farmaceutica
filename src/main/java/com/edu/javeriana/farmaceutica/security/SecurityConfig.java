package com.edu.javeriana.farmaceutica.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ClienteDatailsService clienteDatailsService;
    private final ProveedorDatailsService proveedorDatailsService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(clienteAuthenticationProvider())
            .authenticationProvider(proveedorAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .cors().disable()
            .csrf().disable()
            .authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true);
    }

    /*
    .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagge‌​r-ui.html", "/swagger-resources/configuration/security").permitAll()
            .antMatchers(HttpMethod.POST, "/api/v1/clientes").permitAll()
            .antMatchers(HttpMethod.POST, "/api/v1/proveedores").permitAll()
            .anyRequest().authenticated()
     */

    @Bean
    DaoAuthenticationProvider clienteAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(clienteDatailsService);
        return provider;
    }

    @Bean
    DaoAuthenticationProvider proveedorAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(proveedorDatailsService);
        return provider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
