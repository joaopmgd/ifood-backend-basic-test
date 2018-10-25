package com.ifood.domain.loggerError;

public class WeatherError {

    private int cod;
    private String message;

    public WeatherError(int cod, String message) {
        this.cod = cod;
        this.message = message;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WeatherError{" +
                "cod=" + cod +
                ", message='" + message + '\'' +
                '}';
    }
}
