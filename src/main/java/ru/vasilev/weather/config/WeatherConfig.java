package ru.vasilev.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vasilev.weather.dao.CityDao;
import ru.vasilev.weather.dao.CityDaoImpl;
import ru.vasilev.weather.yandex.ApiWeather;
import ru.vasilev.weather.yandex.YandexWeatherApi;

@Configuration
public class WeatherConfig {
    @Bean
    public CityDao getWeatherDao() {
        return new CityDaoImpl();
    }

    @Bean
    public ApiWeather getApiWeather() {
        return new YandexWeatherApi();
    }
}
