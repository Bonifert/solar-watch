package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.openweather.OpenWeatherCoordinates;
import com.codecool.solarwatch.model.sunrisesunset.SunReport;
import com.codecool.solarwatch.service.OpenWeatherService;
import com.codecool.solarwatch.service.SunriseSunsetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class SunController {
  private final SunriseSunsetService sunriseSunsetService;
  private final OpenWeatherService openWeatherService;
  
  public SunController(SunriseSunsetService sunriseSunsetService, OpenWeatherService openWeatherService) {
    this.sunriseSunsetService = sunriseSunsetService;
    this.openWeatherService = openWeatherService;
  }
  
  @GetMapping("/getsunrisesunset")
  public ResponseEntity<?> getSunriseAndSunset(@RequestParam String city, @RequestParam LocalDate date){
    
    Optional<OpenWeatherCoordinates> coordinates = openWeatherService.getCoordinates(city);
    if (coordinates.isEmpty()) return ResponseEntity.badRequest().body("Invalid city!");
    
    SunReport report = sunriseSunsetService.getSunriseAndSunset(coordinates.get(), date);
    return ResponseEntity.ok().body(report);
  }
}
