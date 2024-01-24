package com.dice.Weather_Report.exception;

public class WeatherForecastNotFoundException extends RuntimeException
{
    public WeatherForecastNotFoundException(String msg)
    {
        super(msg);
    }
}
