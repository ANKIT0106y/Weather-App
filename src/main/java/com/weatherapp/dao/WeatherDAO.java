package com.weatherapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.weatherapp.model.WeatherData;

public class WeatherDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/WeatherAppDB"; 
    private String jdbcUserName = "root"; 
    private String jdbcPassword = "root"; 

    // SQL Queries
    private static final String INSERT_WEATHER_SQL = 
        "INSERT INTO WeatherData (city_name, temperature, humidity, wind_speed, weather_condition) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_WEATHER_BY_ID = 
        "SELECT * FROM WeatherData WHERE id = ?";
    private static final String DELETE_WEATHER_SQL = 
        "DELETE FROM WeatherData WHERE id = ?";
    private static final String UPDATE_WEATHER_SQL = 
        "UPDATE WeatherData SET city_name = ?, temperature = ?, humidity = ?, wind_speed = ?, weather_condition = ? WHERE id = ?";

    // Establish database connection
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
        return connection;
    }

    // Insert Weather Data
    public void insertWeatherData(WeatherData weather) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WEATHER_SQL)) {

            preparedStatement.setString(1, weather.getCityName());
            preparedStatement.setFloat(2, weather.getTemperature());
            preparedStatement.setInt(3, weather.getHumidity());
            preparedStatement.setFloat(4, weather.getWindSpeed());
            preparedStatement.setString(5, weather.getWeatherCondition());

            int rowsInserted = preparedStatement.executeUpdate(); // Execute the query
            System.out.println("Rows inserted: " + rowsInserted);

        } catch (SQLException e) {
            System.err.println("Error in insertWeatherData: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Select Weather Data by ID
    public WeatherData selectWeatherById(int id) {
        WeatherData weather = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WEATHER_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process result set
            if (resultSet.next()) {
                weather = new WeatherData();
                weather.setId(resultSet.getInt("id"));
                weather.setCityName(resultSet.getString("city_name"));
                weather.setTemperature(resultSet.getFloat("temperature"));
                weather.setHumidity(resultSet.getInt("humidity"));
                weather.setWindSpeed(resultSet.getFloat("wind_speed"));
                weather.setWeatherCondition(resultSet.getString("weather_condition"));
                weather.setRecordedTime(resultSet.getTimestamp("recorded_time"));
            } else {
                System.out.println("No weather data found with ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Error in selectWeatherById: " + e.getMessage());
            e.printStackTrace();
        }
        return weather;
    }

    // Delete Weather Data by ID
    public boolean deleteWeather(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WEATHER_SQL)) {

            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0; // Check if a row was deleted
            System.out.println("Rows deleted: " + (rowDeleted ? 1 : 0));

        } catch (SQLException e) {
            System.err.println("Error in deleteWeather: " + e.getMessage());
            e.printStackTrace();
        }
        return rowDeleted;
    }

    // Update Weather Data
    public boolean updateWeather(WeatherData weather) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_WEATHER_SQL)) {

            preparedStatement.setString(1, weather.getCityName());
            preparedStatement.setFloat(2, weather.getTemperature());
            preparedStatement.setInt(3, weather.getHumidity());
            preparedStatement.setFloat(4, weather.getWindSpeed());
            preparedStatement.setString(5, weather.getWeatherCondition());
            preparedStatement.setInt(6, weather.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0; // Check if a row was updated
            System.out.println("Rows updated: " + (rowUpdated ? 1 : 0));

        } catch (SQLException e) {
            System.err.println("Error in updateWeather: " + e.getMessage());
            e.printStackTrace();
        }
        return rowUpdated;
    }
}