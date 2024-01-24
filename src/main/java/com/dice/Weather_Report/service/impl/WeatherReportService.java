package com.dice.Weather_Report.service.impl;


import com.dice.Weather_Report.exception.WeatherForecastNotFoundException;
import com.dice.Weather_Report.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@Slf4j
@Service
class WeatherReportService implements WeatherService
{

    private static final String API_KEY = "974ca5b46amsh09c98409888a96bp114b84jsn412bded75aad";
    private static final String HOST = "forecast9.p.rapidapi.com";

   // private static final String BASE_URL = "https://rapidapi.com/wettercom-wettercom-default/api/forecast9/";
   private static final String BASE_URL = "https://forecast9.p.rapidapi.com/";


    private final RestTemplate restTemplate;

    public WeatherReportService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getForecastSummary(String city)
    {


        log.info("*** Getting Weather forecast summery  for city :: {}", city);

        HttpHeaders        headers = createHeaders();
        HttpEntity<String> entity  = new HttpEntity<>(headers);
        String response = restTemplate.exchange(
                BASE_URL + "RapidApiGetForecastSummaryByLocationName/" + city,
                HttpMethod.GET,
                entity,
                String.class
        ).getBody();

        if (response==null) {
            throw new WeatherForecastNotFoundException("Weather forecast for tomorrow not found for provided zipcode");
        }
        return response;
    }

    @Override
    public String getHourlyForecast(String city)
    {
        log.info("*** Getting hourly Weather forecast for city :: {}", city);

        HttpHeaders        headers = createHeaders();
        HttpEntity<String> entity  = new HttpEntity<>(headers);
        return restTemplate.exchange(
                BASE_URL + "RapidApiGetHourlyForecastByLocationName/" + city,
                HttpMethod.GET,
                entity,
                String.class
        ).getBody();
    }

    private HttpHeaders createHeaders()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", API_KEY);
        headers.set("X-RapidAPI-Host", HOST);
        return headers;
    }
}