package ru.vasilev.weather.data;

import com.google.gson.annotations.SerializedName;

public class WeatherData {
    @SerializedName("temp")
    private int temperature;
    @SerializedName("wind_speed")
    private double windSpeed;
    @SerializedName("pressure_mm")
    private int pressure;
    @SerializedName("condition")
    private String condition;
    @SerializedName("icon")
    private String icon;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
