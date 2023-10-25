package com.codecool.solarwatch.model.sunrisesunset;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SunriseSunsetReportResults(String sunrise, String sunset) {
}
