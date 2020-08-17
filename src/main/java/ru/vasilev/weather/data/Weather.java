package ru.vasilev.weather.data;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("fact")
    WeatherData weatherFact;

    public WeatherData getWeatherFact() {
        return weatherFact;
    }

    public void setWeatherFact(WeatherData weatherFact) {
        this.weatherFact = weatherFact;
    }
}
