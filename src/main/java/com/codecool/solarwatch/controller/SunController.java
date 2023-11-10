package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.model.sunrisesunset.SunReport;
import com.codecool.solarwatch.service.OpenWeatherService;
import com.codecool.solarwatch.service.SunriseSunsetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/solar")
public class SunController {
  private final SunriseSunsetService sunriseSunsetService;
  private final OpenWeatherService openWeatherService;

  public SunController(SunriseSunsetService sunriseSunsetService, OpenWeatherService openWeatherService) {
    this.sunriseSunsetService = sunriseSunsetService;
    this.openWeatherService = openWeatherService;
  }

  @GetMapping("/getsunrisesunset")
  public ResponseEntity<SunReport> getSunriseAndSunset(@RequestParam String city, @RequestParam LocalDate date){
    City searchedCity = openWeatherService.getCoordinates(city);
    SunReport report = sunriseSunsetService.getSunriseAndSunset(searchedCity, date);
    return ResponseEntity.ok().body(report);
  }
}
