package com.codecool.solarwatch.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String subject, long id) {
    super(subject + " with id " + id + " not found.");
  }

  public NotFoundException(String message) {
    super(message);
  }
}
