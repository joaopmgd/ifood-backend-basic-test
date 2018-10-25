package com.ifood.domain;

import java.util.ArrayList;
import java.util.List;

public class CityWeather {

    private List<Weather> weather;
    private String city;
    private WeatherDetails weatherDetails;


    public CityWeather() {
        this.weather = new ArrayList<>();
        this.city = "";
        this.weatherDetails = new WeatherDetails();
    }

    public CityWeather(List<Weather> weather, String city, WeatherDetails weatherDetails) {
        this.weather = weather;
        this.city = city;
        this.weatherDetails = weatherDetails;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public WeatherDetails getWeatherDetails() {
        return weatherDetails;
    }

    public void setWeatherDetails(WeatherDetails weatherDetails) {
        this.weatherDetails = weatherDetails;
    }

    @Override
    public String toString() {
        return "CityWeather{" +
                "weather=" + weather +
                ", city='" + city + '\'' +
                ", weatherDetails=" + weatherDetails +
                '}';
    }
}
