package com.prueb.tecnica.paises.security;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prueb.tecnica.paises.exception.ResourceNotFundExcepton;
import com.prueb.tecnica.paises.model.User;
import com.prueb.tecnica.paises.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usersRepository;

    private User userDetails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Dentro de loadUserByUsername", username);

        userDetails = usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese emaill: " + username));

        if (!Objects.isNull(userDetails)) {
            return new org.springframework.security.core.userdetails.User(userDetails.getEmail(),
                    userDetails.getPassword(), new ArrayList<>());
        } else {
            throw new ResourceNotFundExcepton("Usuario no encontrado");
        }
    }

    public User getUserDatil() {
        return userDetails;
    }

}
