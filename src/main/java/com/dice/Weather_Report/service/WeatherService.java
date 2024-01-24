package com.dice.Weather_Report.service;

public interface WeatherService
{
    String getForecastSummary(String city);

    String getHourlyForecast(String city);
}
