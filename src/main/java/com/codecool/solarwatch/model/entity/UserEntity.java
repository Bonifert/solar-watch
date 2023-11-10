package com.codecool.solarwatch.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserEntity{
  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role role;
}