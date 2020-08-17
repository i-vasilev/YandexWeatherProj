package ru.vasilev.weather.yandex;

import ru.vasilev.weather.data.City;
import ru.vasilev.weather.data.Weather;

public interface ApiWeather {
    Weather getWeatherByCity(City city);
}
