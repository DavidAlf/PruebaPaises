package com.prueb.tecnica.paises.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prueb.tecnica.paises.exception.ResourceNotFundExcepton;
import com.prueb.tecnica.paises.model.Countries;
import com.prueb.tecnica.paises.repository.CountriesRepository;
import com.prueb.tecnica.paises.service.impl.CountriesServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    private CountriesRepository countryRepository;

    @InjectMocks
    private CountriesServiceImpl countryService;

    private Countries country;

    @BeforeEach
    void setup() {
        country = Countries.builder()
                .id(1L)
                .country("COLOMBIA")
                .capital("Bogota")
                .population(10L)
                .currency("Pesos - COP")
                .build();
    }

    @DisplayName("Test que valida la insercion")
    @Test
    void testSaveCountry() {
        // > Given
        given(countryRepository.findByCountry(country.getCountry())).willReturn(Optional.empty());
        given(countryRepository.save(country)).willReturn(country);

        // // >Then
        Countries countrySave = countryService.saveCountry(country);

        // // >When
        assertThat(countrySave).isNotNull();
    }

    @DisplayName("Test que valida la insercion falla por que ya existe")
    @Test
    void testSaveCountryWithException() {
        // > Given
        given(countryRepository.findByCountry(country.getCountry())).willReturn(Optional.of(country));

        // // >Then
        assertThrows(ResourceNotFundExcepton.class, () -> {
            countryService.saveCountry(country);
        });

        // // >When
        verify(countryRepository, never()).save(any(Countries.class));
    }

    @DisplayName("Test para optener datos por pais")
    @Test
    void testGetDataByCountry() {
        // >Given
        given(countryRepository.findByCountry(country.getCountry())).willReturn(Optional.of(country));

        // >Then
        Countries countrySave = countryService.getDataByCountry(country.getCountry()).get();

        // >When
        assertThat(countrySave).isNotNull();
    }
}
