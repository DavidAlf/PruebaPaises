package com.prueb.tecnica.paises.service;

import java.util.Optional;

import com.prueb.tecnica.paises.model.Countries;

public interface CountriesService {

    Countries saveCountry(Countries country);

    Optional<Countries> getDataByCountry(String country);

}
