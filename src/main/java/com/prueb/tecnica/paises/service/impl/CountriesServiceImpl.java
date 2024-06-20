package com.prueb.tecnica.paises.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueb.tecnica.paises.exception.ResourceNotFundExcepton;
import com.prueb.tecnica.paises.model.Countries;
import com.prueb.tecnica.paises.repository.CountriesRepository;
import com.prueb.tecnica.paises.service.CountriesService;

@Service
public class CountriesServiceImpl implements CountriesService {

    private static final Logger logger = LoggerFactory.getLogger(CountriesServiceImpl.class);

    @Autowired
    private CountriesRepository countriesRepository;

    @Override
    @Transactional
    public Countries saveCountry(Countries country) {
        logger.info("Inicio de saveCountry");
        Optional<Countries> countrySaved = countriesRepository.findByCountry(country.getCountry());

        if (countrySaved.isPresent()) {
            throw new ResourceNotFundExcepton("El pais ya existe: " + country.getCountry());
        }

        return countriesRepository.save(country);
    }

    @Override
    @Transactional
    public Optional<Countries> getDataByCountry(String country) {
        logger.info("Inicio de getDataByCountry");
        return countriesRepository.findByCountry(country);
    }

}
