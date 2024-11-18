package com.weatherapp.model;

import java.sql.Timestamp;

public class WeatherData {

    private int id;
    private String cityName;
    private float temperature;
    private int humidity;
    private float windSpeed;
    private String weatherCondition;
    private Timestamp recordedTime;

    // Default Constructor
    public WeatherData() {
    }

    // Parameterized Constructor
    public WeatherData(int id, String cityName, float temperature, int humidity, float windSpeed, String weatherCondition, Timestamp recordedTime) {
        this.id = id;
        this.cityName = cityName;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.weatherCondition = weatherCondition;
        this.recordedTime = recordedTime;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public Timestamp getRecordedTime() {
        return recordedTime;
    }

    public void setRecordedTime(Timestamp recordedTime) {
        this.recordedTime = recordedTime;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", weatherCondition='" + weatherCondition + '\'' +
                ", recordedTime=" + recordedTime +
                '}';
    }
}