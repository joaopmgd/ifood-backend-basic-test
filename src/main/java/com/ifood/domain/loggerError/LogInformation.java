package com.ifood.domain.loggerError;

public class LogInformation {

    /**
     * Possible HTTP Responses
     */
    public static final String cityMustBeInformed = "City must be informed in the path.";
    public static final String requestDataIsWrong = "Request data was not accepted.";

    /**
     * Log Values
     */
    public static final String consultingCityValue = "Consulting weather for %s was ok.";
    public static final String getCityWeatherInit = "GetCityWeather for city %s";
    public static final String consultingCityWeatherInit = "GetCityWeather consulting for city %s";
    public static final String requestingOpenWeather = "Requesting OpenWeather for city %s";
    public static final String HystrixFallbackRecovery = "Hystrix Fallback Recovered from an error and is consulting another API.";


    /**
     * Error Values
     */
    public static final String invalidCityValue = "User entered an empty city or none: %s";
    public static final String errorInTheWeatherRequest = "There was an error recovering the weather for %s";
    public static final String WeatherBitCouldNotFindCity = "Fallback WeatherBit API could not find weather for the city %s";
}
