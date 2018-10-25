package com.ifood.domain.loggerError;

public class LogInformation {

    /**
     * Possible HTTP Responses
     */
    public static final String cityMustBeInformed = "City must be informed in the path.";
    public static final String weatherServiceIsUnreachable = "Weather service is unreachable.";

    /**
     * Log Values
     */
    public static final String consultingCityValue = "Consulting weather for %s was ok.";
    public static final String getCityWeatherInit = "GetCityWeather for city %s";
    public static final String consultingCityWeatherInit = "GetCityWeather consulting for city %s";


    /**
     * Error Values
     */
    public static final String invalidCityValue = "User entered an empty city or none: %s";
    public static final String errorInTheWeatherRequest = "There was an error recovering the weather for %s";
    public static final String exceptionDetails = "Exception details %s";
}
