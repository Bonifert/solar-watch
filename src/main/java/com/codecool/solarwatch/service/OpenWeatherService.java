package com.codecool.solarwatch.service;

import com.codecool.solarwatch.exception.NotSupportedCityException;
import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.service.repository.CityRepository;
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
  private final CityRepository cityRepository;

  @Autowired
  public OpenWeatherService(RestTemplate restTemplate, CityRepository cityRepository) {
    this.restTemplate = restTemplate;
    this.cityRepository = cityRepository;
  }

  public City getCoordinates(String city) {
    Optional<City> searchedCity = cityRepository.findCityByName(city);
    if (searchedCity.isPresent()) {
      System.out.println("El van mentve gecooo");
      return searchedCity.get();
    }
    String url = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s", city, API_KEY);
    City[] response = restTemplate.getForObject(url, City[].class);
    if (response != null && response.length > 0) {
      logger.info("The answer from OpenWeather api is: " + response[0]);
      cityRepository.save(response[0]);
      return response[0];
    }
    logger.error("We didn't get response from the OpenWeather server");
    throw new NotSupportedCityException(String.format("%s is not supported or not exist.", city));
  }
}
