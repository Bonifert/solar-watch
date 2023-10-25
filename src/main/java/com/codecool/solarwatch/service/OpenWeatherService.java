package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.openweather.OpenWeatherCoordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OpenWeatherService {
  private static final String API_KEY = "e37b743264d20c2a7ecafe11d326378d";
  private static final Logger logger = LoggerFactory.getLogger(OpenWeatherService.class);
  private final RestTemplate restTemplate;
  
  @Autowired
  public OpenWeatherService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
  
  public Optional<OpenWeatherCoordinates> getCoordinates(String city){
    String url = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s", city, API_KEY);
    OpenWeatherCoordinates[] response = restTemplate.getForObject(url, OpenWeatherCoordinates[].class);
    
    if (response != null && response.length > 0) {
      logger.info("The answer from OpenWeather api is: " + response[0]);
      return Optional.of(response[0]);
    }
    logger.error("We didn't get response from the OpenWeather server");
    return Optional.empty();
  }
}
