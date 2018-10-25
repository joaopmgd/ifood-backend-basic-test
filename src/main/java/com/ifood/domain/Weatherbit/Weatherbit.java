package com.ifood.domain.Weatherbit;

import java.util.List;

public class Weatherbit {

    private List<WeatherbitData> data;

    public Weatherbit() {
    }

    public Weatherbit(List<WeatherbitData> data) {
        this.data = data;
    }

    public List<WeatherbitData> getData() {
        return data;
    }

    public void setData(List<WeatherbitData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Weatherbit{" +
                "data=" + data +
                '}';
    }
}
