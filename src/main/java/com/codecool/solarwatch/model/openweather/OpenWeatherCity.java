package com.codecool.solarwatch.model.openweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherCity(float lat, float lon, String country, String state) {
}
