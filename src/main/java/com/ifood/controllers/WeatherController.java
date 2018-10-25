package com.ifood.controllers;

import com.google.gson.Gson;
import com.ifood.domain.CityWeather;
import com.ifood.domain.loggerError.LogInformation;
import com.ifood.domain.loggerError.WeatherError;
import com.ifood.services.WeatherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

@Controller
@RequestMapping(path = "/weather")
public class WeatherController {

    private static Logger logger = Logger.getLogger(WeatherController.class.getName());

    /**
     * WeatherService injects its dependencies to make its functionalities accessible to the controller
     */

    @Autowired
    private WeatherService weatherService;

    /**
     * GetCityWeather will receive an HTTP Request evaluates the weather of the indicated parameter
     *
     * @param city the string containing the location that the service will recover the weather information
     *             if the string is empty or null a proper error will be shown
     */
    @GetMapping
    public ResponseEntity GetCityWeather(@RequestParam(required=false) String city) {
        logger.info(String.format(LogInformation.getCityWeatherInit, city));
        if (city == null || city.isEmpty()){
            logger.error(String.format(LogInformation.invalidCityValue, city));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new WeatherError(HttpStatus.BAD_REQUEST.value(), LogInformation.cityMustBeInformed));
        }
        try{
            CityWeather cityWeather = this.weatherService.GetCityWeather(city);
            logger.info(String.format(LogInformation.consultingCityValue, city));
            return ResponseEntity.status(HttpStatus.OK).body(cityWeather);
        }catch (HttpClientErrorException e){
            logger.error(e.getResponseBodyAsString());
            return ResponseEntity.status(e.getStatusCode())
                    .body(new Gson().fromJson(e.getResponseBodyAsString(), WeatherError.class));
        }catch (Exception e){
            logger.error(LogInformation.weatherServiceIsUnreachable);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(new WeatherError(HttpStatus.BAD_GATEWAY.value(), LogInformation.weatherServiceIsUnreachable));
        }
    }
}
