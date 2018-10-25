package com.ifood.services;

import com.ifood.domain.CityWeather;
import com.ifood.domain.OpenWeather;
import com.ifood.domain.loggerError.LogInformation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {

    private static Logger logger = Logger.getLogger(WeatherService.class.getName());

    /**
     * appid is my token to consume the openweathermap api
     */
    @Value("${cityWeather.appid}")
    private String appid;

    /**
     * cityWeatherEndpoint is the endpoint that will retrieve the city weather information
     */
    @Value("${cityWeather.endpoint}")
    private String cityWeatherEndpoint;

    /**
     * GetCityWeather function will make an GET HTTP request for openweathermap
     * If the answer is ok the object will be parsed to the CityWeather class to make it more user friendly
     *
     * @param city the string containing the location that the service will recover the weather information
     *             if the string is empty or null a proper error will be shown
     */
    @Cacheable("CityWeather")
    public CityWeather GetCityWeather(String city){
        logger.info(String.format(LogInformation.consultingCityWeatherInit, city));
        try{
            Map<String, String> params = new HashMap<>();
            params.put("q", city);
            params.put("appid", appid);
            RestTemplate restTemplate = new RestTemplate();
            OpenWeather openWeather = restTemplate.getForObject(cityWeatherEndpoint, OpenWeather.class, params);
            return new CityWeather(openWeather.getWeather(), city, openWeather.getMain());
        }catch (Exception e){
            logger.info(String.format(LogInformation.errorInTheWeatherRequest, city));
            logger.error(String.format(LogInformation.exceptionDetails, e));
            throw e;
        }
    }
}
