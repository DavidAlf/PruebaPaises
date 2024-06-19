package com.prueb.tecnica.paises.controllerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueb.tecnica.paises.model.Countries;
import com.prueb.tecnica.paises.service.CountriesService;

@WebMvcTest
public class CountryController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CountriesService countryService;

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

    @DisplayName("[Controller] Test para guardar pais")
    @Test
    void testSaveCountry() throws Exception {
        // > Given
        given(countryService.saveCountry(any(Countries.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // > When
        ResultActions response = mockMvc.perform(post("/api/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(country)));

        // > Then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.country", is(country.getCountry())))
                .andExpect(jsonPath("$.capital", is(country.getCapital())))
                .andExpect(jsonPath("$.currency", is(country.getCurrency())));
    }

    @DisplayName("[Controller] Test para optener datos x pais")
    @Test
    void testGetDataByCountry() throws Exception {
        // >Given
        String countryName = "COLOMBIA";

        given(countryService.getDataByCountry(countryName)).willReturn(Optional.of(country));

        // >When
        ResultActions response = mockMvc.perform(get("/api/countries/{country}", countryName));

        // >Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.countries.country", is(country.getCountry())))
                .andExpect(jsonPath("$.countries.capital", is(country.getCapital())))
                .andExpect(jsonPath("$.countries.currency", is(country.getCurrency())));
    }

    @DisplayName("[Controller] Test para optener datos x pais no encontrado")
    @Test
    void testGetDataByCountryNotFound() throws Exception {
        // >Given
        String countryName = "x";

        given(countryService.getDataByCountry(countryName)).willReturn(Optional.empty());

        // >When
        ResultActions response = mockMvc.perform(get("/api/countries/{country}", countryName));

        // >Then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }
}
