package ru.vasilev.weather.dao;

public enum Queries {

    GET_ALL_CITIES("SELECT id, name FROM city WHERE lower(name) like lower(?);"),
    GET_CITY_BY_ID("SELECT id, name, lon, lat FROM city WHERE id = ?;");

    private final String value;

    /**
     * Creates instance.
     *
     * @param value query.
     */
    Queries(String value) {
        this.value = value;
    }

    /**
     * Gets value of instance.
     *
     * @return query for database.
     */
    public String getValue() {
        return value;
    }
}
