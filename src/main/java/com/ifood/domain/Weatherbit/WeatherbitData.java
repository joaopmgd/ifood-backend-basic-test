package com.ifood.domain.Weatherbit;

public class WeatherbitData {

    private double temp;
    private double pres;
    private double rh;
    private WeatherbitDescription weather;

    public WeatherbitData() {
    }

    public WeatherbitData(double temp, double pres, double rh, WeatherbitDescription weather) {
        this.temp = temp;
        this.pres = pres;
        this.rh = rh;
        this.weather = weather;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPres() {
        return pres;
    }

    public void setPres(double pres) {
        this.pres = pres;
    }

    public double getRh() {
        return rh;
    }

    public void setRh(double rh) {
        this.rh = rh;
    }

    public WeatherbitDescription getWeather() {
        return weather;
    }

    public void setWeather(WeatherbitDescription weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WeatherbitData{" +
                "temp=" + temp +
                ", pres=" + pres +
                ", rh=" + rh +
                ", weather=" + weather +
                '}';
    }
}
