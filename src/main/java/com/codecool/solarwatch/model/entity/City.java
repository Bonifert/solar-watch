package com.codecool.solarwatch.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class City {
  @Id
  @GeneratedValue
  private Long id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private float lon;
  @Column(nullable = false)
  private float lat;
  @Column(nullable = true)
  private String state;
  @Column(nullable = false)
  private String country;
}
