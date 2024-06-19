package com.prueb.tecnica.paises.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.prueb.tecnica.paises.model.Countries;
import com.prueb.tecnica.paises.repository.CountriesRepository;

@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private CountriesRepository countriesRepository;

    private Countries country;

    @BeforeEach
    void setup() {
        country = Countries.builder()
                .id(1L)
                .country("COLOMBIA")
                .capital("Bogotá")
                .population(10L)
                .currency("Pesos - COP")
                .build();
    }

    @DisplayName("Test para guardar un pais")
    @Test
    void testSaveCountry() {
        // BDD
        // -> given: condicion previa o configuracion
        Countries country1 = Countries.builder()
                .id(2L)
                .country("Suiza")
                .capital("Berna")
                .population(10L)
                .currency("Francos - FR")
                .build();

        // -> when: Accion o comportamiento que vamos a probar
        Countries countrySaved = countriesRepository.save(country1);

        // -> then: verificacion de dalida
        assertThat(countrySaved).isNotNull();
        assertThat(country1.getId()).isGreaterThan(0);
    }

    @DisplayName("Test para optener datos de un pais por su nombre")
    @Test
    public void testGetDataByCountry() {
        // BDD
        // -> given: condicion previa o configuracion
        Countries country1 = country;

        // -> when: Accion o comportamiento que vamos a probar
        Countries countryBD = countriesRepository.findByCountry(country1.getCountry()).get();

        // -> then: verificacion de dalida
        assertThat(countryBD).isNotNull();
        assertThat(countryBD.getCountry()).isEqualTo(country.getCountry());
        assertThat(countryBD.getCapital()).isEqualTo(country.getCapital());
    }

}
