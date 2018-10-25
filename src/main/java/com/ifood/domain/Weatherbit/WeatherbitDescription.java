package com.ifood.domain.Weatherbit;

public class WeatherbitDescription {

    private String description;

    public WeatherbitDescription() {
    }

    public WeatherbitDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WeatherbitDescription{" +
                "description='" + description + '\'' +
                '}';
    }
}
