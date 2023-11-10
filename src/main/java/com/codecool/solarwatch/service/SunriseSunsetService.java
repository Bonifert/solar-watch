package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.model.entity.SunriseSunset;
import com.codecool.solarwatch.model.sunrisesunset.SunReport;
import com.codecool.solarwatch.model.sunrisesunset.SunriseSunsetReport;
import com.codecool.solarwatch.service.repository.SunriseSunsetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class SunriseSunsetService {
  private static final DateTimeFormatter FORMATTER_FOR_LOCALTIME = DateTimeFormatter.ofPattern("h:mm:ss a");
  private static final DateTimeFormatter FORMATTER_FOR_LOCALDATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final Logger logger = LoggerFactory.getLogger(SunriseSunsetService.class);
  private final RestTemplate restTemplate;
  private final SunriseSunsetRepository sunriseSunsetRepository;

  @Autowired
  public SunriseSunsetService(RestTemplate restTemplate, SunriseSunsetRepository sunriseSunsetRepository) {
    this.restTemplate = restTemplate;
    this.sunriseSunsetRepository = sunriseSunsetRepository;
  }

  public SunReport getSunriseAndSunset(City city, LocalDate date) {
    Optional<SunriseSunset> savedSunriseSunset = sunriseSunsetRepository.findSunriseSunsetByCityAndDate(city, date);
    if (savedSunriseSunset.isPresent()) {
      System.out.println("Sunrise sunset is mentve");
      return new SunReport(savedSunriseSunset.get().getSunrise(), savedSunriseSunset.get().getSunset());
    }
    String url = String.format("https://api.sunrise-sunset.org/json?lat=%f&lng=%f&date=%s",
                               city.getLat(),
                               city.getLon(),
                               date.format(FORMATTER_FOR_LOCALDATE));
    SunriseSunsetReport response = restTemplate.getForObject(url, SunriseSunsetReport.class);
    if (response != null) {
      SunriseSunset newSunriseSunset = SunriseSunset.builder()
                                                    .city(city)
                                                    .sunrise(LocalTime.parse(response.results().sunrise(),
                                                                             FORMATTER_FOR_LOCALTIME))
                                                    .sunset(LocalTime.parse(response.results().sunset(),
                                                                            FORMATTER_FOR_LOCALTIME))
                                                    .date(date)
                                                    .build();
      sunriseSunsetRepository.save(newSunriseSunset);
      return new SunReport(newSunriseSunset.getSunrise(), newSunriseSunset.getSunset());
    }
    throw new RuntimeException("We didn't get any response from sunrise-sunset api.");
  }
}
