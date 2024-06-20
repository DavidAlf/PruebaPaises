package com.prueb.tecnica.paises.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueb.tecnica.paises.model.ResponseDTO;
import com.prueb.tecnica.paises.model.ResponseErrorDTO;
import com.prueb.tecnica.paises.model.User;
import com.prueb.tecnica.paises.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> getLogin(@RequestBody(required = true) Map<String, String> requestMap) {
        System.out.println("Inicio de getLogin");
        try {
            
            log.info("Inicio de getLogin");
            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(),
                    userService.getLogin(requestMap));

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.error("Error getLogin " + e.getMessage());
            ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(HttpStatus.NOT_FOUND.value(),
                    new User(), "No se encuentra el pais");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseErrorDTO);
        }
    }
}
