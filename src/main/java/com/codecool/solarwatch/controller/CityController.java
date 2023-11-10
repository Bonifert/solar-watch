package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
  private final CityService cityService;

  public CityController(CityService cityService) {
    this.cityService = cityService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<City>> getAllCity(){
    return ResponseEntity.ok().body(cityService.getAllCity());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCityById(@PathVariable long id){
    cityService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
