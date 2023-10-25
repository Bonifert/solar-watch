package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.openweather.OpenWeatherCoordinates;
import com.codecool.solarwatch.model.sunrisesunset.SunReport;
import com.codecool.solarwatch.model.sunrisesunset.SunriseSunsetReport;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;

@Service
public class SunriseSunsetService {
  private static final DateTimeFormatter FORMATTER_FOR_LOCALTIME = DateTimeFormatter.ofPattern("h:mm:ss a");
  private static final DateTimeFormatter FORMATTER_FOR_LOCALDATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private final RestTemplate restTemplate;
  private static final Logger logger = LoggerFactory.getLogger(SunriseSunsetService.class);
  
  @Autowired
  public SunriseSunsetService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
  
  public SunReport getSunriseAndSunset(OpenWeatherCoordinates coordinates, LocalDate date){
    String url = String.format("https://api.sunrise-sunset.org/json?lat=%f&lng=%f&date=%s", coordinates.lat(), coordinates.lon(), date.format(FORMATTER_FOR_LOCALDATE));
    SunriseSunsetReport response = restTemplate.getForObject(url, SunriseSunsetReport.class);

    logger.info("Response from the SunriseSunset api: " + response);
    
    return new SunReport(LocalTime.parse(response.results().sunrise(), FORMATTER_FOR_LOCALTIME), LocalTime.parse(response.results().sunset(),
                                                                                                                 FORMATTER_FOR_LOCALTIME));
  }
}
