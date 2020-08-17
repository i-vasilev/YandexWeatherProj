package ru.vasilev.weather.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vasilev.weather.dao.CityDao;
import ru.vasilev.weather.data.City;
import ru.vasilev.weather.data.Weather;
import ru.vasilev.weather.yandex.ApiWeather;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {
    final CityDao cityDao;
    final ApiWeather apiWeather;
    static final String INDEX_PAGE = "index";

    public MainController(CityDao cityDao, ApiWeather apiWeather) {
        this.cityDao = cityDao;
        this.apiWeather = apiWeather;
    }

    @GetMapping("/")
    public String indexPage() {
        return INDEX_PAGE;
    }

    @GetMapping("/search")
    public String searchCity(@RequestParam("cityPartName") String cityPartName, Model model) {
        try {
            final List<City> cities = cityDao.getCities(cityPartName);
            if (!cities.isEmpty()) {
                model.addAttribute("cities", cities);
            } else {
                model.addAttribute("errors", "Города не найдены, пожалуйста, исправьте свой запрос и попробуйте заново!");
                model.addAttribute("cityPartName", cityPartName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return INDEX_PAGE;
    }

    @GetMapping("/weather")
    public String weather(@RequestParam("city") int id, Model model) {
        try {
            final City city = cityDao.getCityById(id);
            if (city == null) {
                model.addAttribute("errors", "Города с таким идентификатором нет!");
            } else {
                final Weather weather = this.apiWeather.getWeatherByCity(city);
                model.addAttribute("weather", weather.getWeatherFact());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return INDEX_PAGE;
    }
}
