package com.dice.Weather_Report.service.impl;

import com.dice.Weather_Report.service.WeatherService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@Service
class WeatherReportService implements WeatherService
{

    private static final String API_KEY = "your-api-key";
    private static final String HOST = "wettercom-wettercom-default.p.rapidapi.com";
    private static final String BASE_URL = "https://rapidapi.com/wettercom-wettercom-default/api/forecast9/";

    private final RestTemplate restTemplate;

    public WeatherReportService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getForecastSummary(String city)
    {
        HttpHeaders        headers = createHeaders();
        HttpEntity<String> entity  = new HttpEntity<>(headers);
        return restTemplate.exchange(
                BASE_URL + "RapidApiGetForecastSummaryByLocationName/" + city,
                HttpMethod.GET,
                entity,
                String.class
        ).getBody();
    }

    @Override
    public String getHourlyForecast(String city)
    {
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