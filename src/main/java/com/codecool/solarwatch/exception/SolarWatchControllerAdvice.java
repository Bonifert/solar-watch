package com.codecool.solarwatch.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class SolarWatchControllerAdvice {
  Logger logger = LoggerFactory.getLogger(SolarWatchControllerAdvice.class);

  @ExceptionHandler(value = NotSupportedCityException.class)
  ResponseEntity<Object> handleNotSupportedCityException(NotSupportedCityException e) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ApiException exception = new ApiException(e.getMessage(), httpStatus, ZonedDateTime.now(ZoneId.of("Z")));
    return new ResponseEntity<>(exception, httpStatus);
  }

  @ExceptionHandler(value = {NotFoundException.class, UsernameNotFoundException.class})
  ResponseEntity<Object> handleNotFoundException(Exception e) {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    ApiException exception = new ApiException(e.getMessage(), httpStatus, ZonedDateTime.now(ZoneId.of("Z")));
    logger.error("Bazki ez baj");
    return new ResponseEntity<>(exception, httpStatus);
  }

}
