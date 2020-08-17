package ru.vasilev.weather.yandex;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.vasilev.weather.data.City;
import ru.vasilev.weather.data.Weather;
import ru.vasilev.weather.http.HttpClient;

import java.util.HashMap;
import java.util.Map;

public class YandexWeatherApi implements ApiWeather {
    @Autowired
    HttpClient httpClient;

    @Value("${url.yandex.weather.api}")
    private String urlYandexWeather;

    @Value("${key.yandex.weather.api}")
    private String keyYandexWeather;

    @Override
    public Weather getWeatherByCity(City city) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("lat", String.valueOf(city.getLat()));
        params.put("lon", String.valueOf(city.getLon()));
        final Map<String, String> headers = new HashMap<>();
        headers.put("X-Yandex-API-Key", keyYandexWeather);
        final String responce = httpClient.get(urlYandexWeather, params, headers);
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        return gson.fromJson(responce, Weather.class);
    }
}
