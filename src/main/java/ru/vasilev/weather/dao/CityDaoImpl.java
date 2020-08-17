package ru.vasilev.weather.dao;

import ru.vasilev.weather.data.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDaoImpl extends Dao implements CityDao {

    @Override
    public List<City> getCities(String cityPartName) throws SQLException {
        return connectAndExecute(connection -> {
            final List<City> cities = new ArrayList<>();
            final PreparedStatement preparedStatement =
                    connection.prepareStatement(Queries.GET_ALL_CITIES.getValue());
            preparedStatement.setString(1, String.format("%%%s%%", cityPartName));
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                final City city = new City();
                city.setId(rs.getInt(1));
                city.setName(rs.getString(2));
                cities.add(city);
            }
            return cities;
        });
    }

    @Override
    public City getCityById(int id) throws SQLException {
        return connectAndExecute(connection -> {
            final PreparedStatement preparedStatement =
                    connection.prepareStatement(Queries.GET_CITY_BY_ID.getValue());
            preparedStatement.setInt(1, id);
            final ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                final City city = new City();
                city.setId(rs.getInt(1));
                city.setName(rs.getString(2));
                city.setLon(rs.getDouble(3));
                city.setLat(rs.getDouble(4));
                return city;
            }
            return null;
        });
    }

}
