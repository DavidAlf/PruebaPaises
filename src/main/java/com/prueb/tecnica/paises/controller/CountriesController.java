package com.prueb.tecnica.paises.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prueb.tecnica.paises.model.Countries;
import com.prueb.tecnica.paises.model.ResponseMsn;
import com.prueb.tecnica.paises.model.ResponseMsnError;
import com.prueb.tecnica.paises.service.CountriesService;

@RestController
@RequestMapping("/api/countries")
public class CountriesController {

    @Autowired
    private CountriesService countriesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Countries saveCountry(@RequestBody Countries country) {
        return countriesService.saveCountry(country);
    }

    @GetMapping("/{country}")
    public ResponseEntity<ResponseMsn> getDataByCountry(@PathVariable("country") String country) {
        try {
            ResponseMsn response = new ResponseMsn(HttpStatus.OK.value(),
                    countriesService.getDataByCountry(country.toUpperCase()).get());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseMsnError responseError = new ResponseMsnError(HttpStatus.NOT_FOUND.value(),
                    new Countries(), "No se encuentra el pais");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
        }

    }
}
