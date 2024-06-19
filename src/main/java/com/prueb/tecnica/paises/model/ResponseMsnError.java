package com.prueb.tecnica.paises.model;

import lombok.Getter;

@Getter
public class ResponseMsnError extends ResponseMsn {

    private String errorMsn;

    public ResponseMsnError(int statusCode, Countries countries, String errorMsn) {
        super(statusCode, countries);
        this.errorMsn = errorMsn;
    }
}
