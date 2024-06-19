package com.prueb.tecnica.paises.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueb.tecnica.paises.exception.ResourceNotFundExcepton;
import com.prueb.tecnica.paises.model.Countries;
import com.prueb.tecnica.paises.repository.CountriesRepository;
import com.prueb.tecnica.paises.service.CountriesService;

@Service
public class CountriesServiceImpl implements CountriesService {

    @Autowired
    private CountriesRepository countriesRepository;

    @Override
    public Countries saveCountry(Countries country) {
        Optional<Countries> countrySaved = countriesRepository.findByCountry(country.getCountry());

        if (countrySaved.isPresent()) {
            throw new ResourceNotFundExcepton("El pais ya existe: " + country.getCountry());
        }

        return countriesRepository.save(country);
    }

    @Override
    public Optional<Countries> getDataByCountry(String country) {
        return countriesRepository.findByCountry(country);
    }

}
