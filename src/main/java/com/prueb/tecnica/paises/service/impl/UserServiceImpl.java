package com.prueb.tecnica.paises.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.prueb.tecnica.paises.security.CustomerDetailsService;
import com.prueb.tecnica.paises.security.jwt.JwtUtil;
import com.prueb.tecnica.paises.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String getLogin(Map<String, String> requestMap) {
        log.info("Dentro de getLogin "+requestMap);

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));

            if (authentication.isAuthenticated()) {
                String email = requestMap.get("email");
                String token = generateJwtToken(email);
                return token;
            } else {
                return "Falta la aprobación del administrador";
            }
        } catch (Exception e) {
            log.error("Error durante la autenticación: {}", e.getMessage());
        }

        return "Credenciales Incorrectas";
    }

    private String generateJwtToken(String email) {
        // Validar si el usuario está activo y obtener el token JWT
        if (customerDetailsService.getUserDatil().getStatus()) {
            return jwtUtil.generateToken(email, customerDetailsService.getUserDatil().getRole());
        } else {
            return "Usuario no activo";
        }
    }
}
