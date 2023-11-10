package com.codecool.solarwatch.model.sunrisesunset;

import java.time.LocalTime;

public record SunReport(LocalTime sunRise, LocalTime sunSet) {
}
