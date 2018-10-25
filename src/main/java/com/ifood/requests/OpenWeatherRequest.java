package com.ifood.requests;

import com.ifood.domain.*;
import com.ifood.domain.Weatherbit.Weatherbit;
import com.ifood.domain.loggerError.LogInformation;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

public class OpenWeatherRequest extends HystrixCommand<OpenWeather> {

    private static Logger logger = Logger.getLogger(OpenWeatherRequest.class.getName());

    /**
     * openWeatherEndpoint is the endpoint that will retrieve the city weather information in OpenWeather
     */
    private String openWeatherEndpoint;

    /**
     * cityWeatherEndpoint is the endpoint that will retrieve the city weather information in weatherBit
     */
    private String weatherBitEndpoint;

    /**
     * params are the parameters that the request needs to fetch the information
     */
    private final Map<String, String> params;

    /**
     * OpenWeatherRequest constructor needs to initialize a Group for Hystrix evaluation
     * openWeatherEndpoint, weatherBitEndpoint and params are also initialized with the endpoint for the OpenWeatherAPI and WeatherBitAPI
     */
    public OpenWeatherRequest(String openWeatherEndpoint, String weatherBitEndpoint, Map<String, String> params) {
        super(HystrixCommandGroupKey.Factory.asKey("CurrentWeather"), 5000);
        this.openWeatherEndpoint = openWeatherEndpoint;
        this.weatherBitEndpoint = weatherBitEndpoint;
        this.params = params;
    }

    /**
     * For Hystrix to extended the "run" function must be implemented
     * In the case Run will make the request for OpenWeather
     * If any errors should occur Hystrix will be able to handle the optimize the message with a Fallback
     */
    @Override
    protected OpenWeather run() {
        logger.info(String.format(LogInformation.requestingOpenWeather, params.get("q")));
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(this.openWeatherEndpoint, OpenWeather.class, this.params);
    }

    /**
     * getFallback is created Overriding Hystrix
     * If any error occur when the Run method is requesting the OpenWeather API, getFallBack can return to the user a better message
     * In this case getFallback will try to get some of the required information from another service called WeatherBit
     */
    @Override
    protected OpenWeather getFallback() {
        logger.error(String.format(LogInformation.errorInTheWeatherRequest, this.params.get("q")));
        logger.info(LogInformation.HystrixFallbackRecovery);
        RestTemplate restTemplate = new RestTemplate();
        Weatherbit weatherbit = restTemplate.getForObject(this.weatherBitEndpoint, Weatherbit.class, this.params);
        Weather weather = new Weather();
        WeatherDetails weatherDetails = new WeatherDetails();
        if (weatherbit.getData().size() > 0) {
            weather.setMain(weatherbit.getData().get(0).getWeather().getDescription());
            weatherDetails.setPressure(weatherbit.getData().get(0).getPres());
            weatherDetails.setHumidity(weatherbit.getData().get(0).getRh());
            weatherDetails.setTemp(weatherbit.getData().get(0).getTemp());
            OpenWeather openWeather = new OpenWeather();
            openWeather.setWeather(Arrays.asList(weather));
            openWeather.setMain(weatherDetails);
            return openWeather;
        } else {
            logger.error(String.format(LogInformation.WeatherBitCouldNotFindCity, this.params.get("q")));
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, LogInformation.requestDataIsWrong);
        }
    }
}
