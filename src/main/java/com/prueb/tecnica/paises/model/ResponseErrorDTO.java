package com.prueb.tecnica.paises.model;

import lombok.Getter;

@Getter
public class ResponseErrorDTO extends ResponseDTO {

    private String errorMsn;

    public ResponseErrorDTO(int statusCode, Object object, String errorMsn) {
        super(statusCode, object);
        this.errorMsn = errorMsn;
    }
}
