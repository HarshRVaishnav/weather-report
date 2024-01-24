package com.dice.Weather_Report.controller;

import com.dice.Weather_Report.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
class WeatherReportController {

    private final WeatherService weatherService;

    public WeatherReportController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecastSummary/{city}")
    public ResponseEntity<String> getForecastSummary(@PathVariable String city) {
        return new ResponseEntity<>(weatherService.getForecastSummary(city), HttpStatus.OK);
    }
    @GetMapping("/hourlyForecast/{city}")
    public ResponseEntity<?> getHourlyForecast(@PathVariable String city) {
        return new ResponseEntity<>(weatherService.getHourlyForecast(city), HttpStatus.OK);
    }

}
