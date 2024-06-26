package com.prueb.tecnica.paises.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueb.tecnica.paises.model.Countries;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, Long> {

    Optional<Countries> findByCountry(@Param(("country")) String country);

}
