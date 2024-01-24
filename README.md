# Weather Report Application

This is a Spring Boot Java application that integrates with the Weather API from RapidAPI. The application exposes RESTful APIs with JSON responses.

## Features

- **API 1**: Get the Weather forecast summary of any city using the `RapidApiGetForecastSummaryByLocationName` API.
- **API 2**: Get hourly Weather forecast details of any city using the `RapidApiGetHourlyForecastByLocationName` API.

## Authentication

The application uses header-based authentication with a random client id and random client secret.

## Usage

To use this application, you need to have Java and Maven installed on your machine. You can then clone this repository and run the application using the following commands:

```bash
git clone https://github.com/yourusername/yourrepository.git
cd yourrepository
mvn spring-boot:run


Once the application is running, you can access the APIs at http://localhost:8080/forecastSummary/{city} and http://localhost:8080/hourlyForecast/{city}.

###API Documentation
For more information about the APIs used in this application, please refer to the RapidAPI documentation.



