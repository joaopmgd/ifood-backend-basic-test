package com.ifood.domain;

import java.util.ArrayList;
import java.util.List;

public class OpenWeather {

    private List<Weather> weather;
    private WeatherDetails main;


    public OpenWeather(List<Weather> weather, WeatherDetails main) {
        this.weather = weather;
        this.main = main;
    }

    public OpenWeather() {
        this.weather = new ArrayList<>();
        this.main = new WeatherDetails();
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public WeatherDetails getMain() {
        return main;
    }

    public void setMain(WeatherDetails main) {
        this.main = main;
    }
    
    @Override
    public String toString() {
        return "OpenWeather{" +
                "weather=" + weather +
                ", main=" + main +
                '}';
    }
}
