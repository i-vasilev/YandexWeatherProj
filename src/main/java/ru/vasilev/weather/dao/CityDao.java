package ru.vasilev.weather.dao;

import org.springframework.stereotype.Component;
import ru.vasilev.weather.data.City;

import java.sql.SQLException;
import java.util.List;

@Component
public interface CityDao {
    List<City> getCities(String cityPartName) throws SQLException;

    City getCityById(int id) throws SQLException;
}
