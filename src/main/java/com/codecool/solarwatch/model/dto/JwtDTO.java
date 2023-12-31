package com.codecool.solarwatch.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtDTO {
  private String jwt;
  private String username;
  private List<String> roles;
}
