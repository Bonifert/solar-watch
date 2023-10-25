package com.codecool.solarwatch.model.openweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherCoordinates(float lat, float lon) {
}
