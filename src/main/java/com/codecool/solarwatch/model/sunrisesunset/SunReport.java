package com.codecool.solarwatch.model.sunrisesunset;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record SunReport(LocalTime sunRise, LocalTime sunSet) {
}
