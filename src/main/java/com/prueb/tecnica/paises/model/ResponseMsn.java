package com.prueb.tecnica.paises.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseMsn {
    private int statusCode;
    private Countries countries;
}
