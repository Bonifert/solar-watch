package com.codecool.solarwatch.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserEntity {
  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role role;
}