package com.ifood.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ifood.domain.loggerError.WeatherError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ifood.domain.loggerError.LogInformation;
import com.ifood.services.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTests {

    @Autowired
    private WeatherController weatherController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    public void verifiesTheCityEvaluation() throws Exception {
        ResponseEntity cityWeather= weatherController.GetCityWeather("");
        assertThat(cityWeather.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(cityWeather.getBody().toString()).isEqualTo(new WeatherError(HttpStatus.BAD_REQUEST.value(), LogInformation.cityMustBeInformed).toString());
        cityWeather= weatherController.GetCityWeather(null);
        assertThat(cityWeather.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(cityWeather.getBody().toString()).isEqualTo(new WeatherError(HttpStatus.BAD_REQUEST.value(), LogInformation.cityMustBeInformed).toString());
    }

    @Test
    public void verifiesTheErrorReturningFromTheRequest() throws Exception {
        when(weatherService.GetCityWeather("London")).thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
        this.mockMvc.perform(get("/weather").param("city", "London")).andExpect(status().isUnauthorized());
    }
}
