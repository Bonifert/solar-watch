package com.codecool.solarwatch.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SunriseSunset {
  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne
  private City city;
  @Column
  private LocalDate date;
  @Column
  private LocalTime sunrise;
  @Column
  private LocalTime sunset;
}
