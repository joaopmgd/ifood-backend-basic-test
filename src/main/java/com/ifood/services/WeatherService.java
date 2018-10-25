package com.ifood.services;

import com.ifood.domain.CityWeather;
import com.ifood.domain.OpenWeather;
import com.ifood.domain.loggerError.LogInformation;
import com.ifood.requests.OpenWeatherRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {

    private static Logger logger = Logger.getLogger(WeatherService.class.getName());

    /**
     * openWeatherEndpoint is the endpoint that will retrieve the city weather information in oepnWeather
     */
    @Value("${endpoint.openWeather}")
    private String openWeatherEndpoint;

    /**
     * cityWeatherEndpoint is the endpoint that will retrieve the city weather information in weatherBit
     */
    @Value("${endpoint.weatherBit}")
    private String weatherBitEndpoint;

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
        Map<String, String> params = new HashMap<>();
        params.put("q", city);
        params.put("appid", System.getenv("OpenWeatherAppid"));
        params.put("key", System.getenv("KeyWeatherbit"));
        OpenWeather openWeather = new OpenWeatherRequest(openWeatherEndpoint, weatherBitEndpoint, params).execute();
        return new CityWeather(openWeather.getWeather(), city, openWeather.getMain());
    }
}
