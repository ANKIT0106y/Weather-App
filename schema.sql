CREATE DATABASE WeatherAppDB;

USE WeatherAppDB;

CREATE TABLE WeatherData (
    id INT AUTO_INCREMENT PRIMARY KEY,
    city_name VARCHAR(50) NOT NULL,
    temperature FLOAT NOT NULL,
    humidity INT NOT NULL,
    wind_speed FLOAT NOT NULL,
    weather_condition VARCHAR(50),
    recorded_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);