package com.prueb.tecnica.paises.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDTO {
    private int statusCode;
    private Object object;
}
