package com.dice.Weather_Report.controller;

import com.dice.Weather_Report.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/weather")
class WeatherReportController {

    private final WeatherService weatherService;

    public WeatherReportController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

//    @Operation(summary = "Get a Weather forecast summery by city")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the data",
//                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
//            @ApiResponse(responseCode = "400", description = "Invalid City name", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Weather forecast not found", content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
//    })
    @GetMapping("/forecastSummery/{city}")
    public ResponseEntity<String> getForecastSummary(@PathVariable @NotNull @NotBlank String city) {
        return new ResponseEntity<>(weatherService.getForecastSummary(city), HttpStatus.OK);
    }

//    @Operation(summary = "Get a hourly Weather forecast by city")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the data",
//                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
//            @ApiResponse(responseCode = "400", description = "Invalid City name", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Weather forecast not found", content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
//    })
    @GetMapping("/hourlyForecast/{city}")
    public ResponseEntity<?> getHourlyForecast(@PathVariable @NotNull @NotBlank String city) {
        return new ResponseEntity<>(weatherService.getHourlyForecast(city), HttpStatus.OK);
    }

}
